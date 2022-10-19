package com.monkey.context.member.service;

import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.domain.MemberProfile;
import com.monkey.context.member.domain.service.OAuthService;
import com.monkey.context.member.domain.service.OauthServiceFactory;
import com.monkey.context.member.dto.oauth.OAuthUserInfo;
import com.monkey.context.member.dto.user.UserProfileUpdateDto;
import com.monkey.context.member.enums.OauthType;
import com.monkey.context.member.infra.repository.UserRepository;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final OauthServiceFactory oAuthServiceFactory;

    @Transactional
    public void updateProfile(MemberId memberId, UserProfileUpdateDto dto) {
        MemberProfile profile = userRepository.findProfileByUserId(memberId).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E000));
        permissionService.checkPermission(memberId, profile);
        profile.update(dto);
        log.info("[{}] - Update Profile", memberId.getId());
    }

    @Transactional
    public void deactivateUser(MemberId memberId) {
        Members members = userRepository.findById(memberId).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E000));
        permissionService.checkPermission(memberId, members);
        members.deactivate();
        log.info("[{}] - Deactivate", memberId.getId());
    }

    @Transactional
    public MemberId getUserIdOrElseCreate(OauthType oauthType, String accessToken) {
        OAuthUserInfo userInfo = getUserInfo(oauthType, accessToken);
        Members result = userRepository.findById(new MemberId(oauthType + "_" + userInfo.getId()))
                .orElseGet(() -> {
                    Members members = userRepository.save(new Members(userInfo));
                    members.setProfile(new MemberProfile(userInfo));
                    log.info("Create User - [{}]", members.getMemberId().getId());
                    return members;
                });
        return result.getMemberId();
    }

    private OAuthUserInfo getUserInfo(OauthType oauthType, String accessToken) {
        OAuthService service = oAuthServiceFactory.getSocialService(oauthType);
        return service.getUserInfo(accessToken);
    }
}
