package com.monkey.context.member.service;

import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.domain.MemberProfile;
import com.monkey.context.member.domain.service.OAuthService;
import com.monkey.context.member.domain.service.OAuthServiceFactory;
import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.member.MemberProfileUpdateDto;
import com.monkey.context.member.dto.oauth.OauthTokenResponseDto;
import com.monkey.context.member.enums.OauthType;
import com.monkey.context.member.infra.repository.MemberRepository;
import com.monkey.context.permission.service.PermissionService;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PermissionService permissionService;
    private final OAuthServiceFactory OAuthServiceFactory;

    @Transactional
    public void updateProfile(MemberId memberId, MemberProfileUpdateDto dto) {
        MemberProfile profile = memberRepository.findProfileByUserId(memberId)
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        permissionService.checkPermission(memberId, profile);
        profile.update(dto);
        log.info("[{}] - Update Profile", memberId.getId());
    }

    @Transactional
    public void deactivateUser(MemberId memberId) {
        Members members = memberRepository.findById(memberId)
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        permissionService.checkPermission(memberId, members);
        members.deactivate();
        log.info("[{}] - Deactivate", memberId.getId());
    }

    @Transactional
    public MemberId getUserIdOrElseCreate(OauthType oauthType, String accessToken) {
        OAuthUserInfoDto userInfo = getUserInfo(oauthType, accessToken);
        return getMemberId(oauthType, userInfo);
    }

    @Transactional
    public MemberId getMember(OauthType oauthType, String authorizationCode) {
        OAuthService service = OAuthServiceFactory.getSocialService(oauthType);
        OauthTokenResponseDto tokenResponseDto = getAccessToken(service, authorizationCode);
        OAuthUserInfoDto userInfoDto = getUserInfo(service, tokenResponseDto.getAccessToken());
        return getMemberId(oauthType, userInfoDto);
    }
    @Transactional
    public MemberId getMemberId(OauthType oauthType, OAuthUserInfoDto userInfo) {
        Members result = memberRepository.findById(new MemberId(oauthType + "_" + userInfo.getId()))
                .orElseGet(() -> {
                    Members members = memberRepository.save(new Members(userInfo));
                    members.setProfile(new MemberProfile(userInfo));
                    log.info("Create User - [{}]", members.getMemberId().getId());
                    return members;
                });
        return result.getMemberId();
    }

    private OauthTokenResponseDto getAccessToken(OAuthService service, String authorizationCode) {
        return service.requestAccessToken(authorizationCode);
    }

    private OAuthUserInfoDto getUserInfo(OAuthService service, String accessToken) {
        return service.getUserInfo(accessToken);
    }

    private OAuthUserInfoDto getUserInfo(OauthType oauthType, String accessToken) {
        OAuthService service = OAuthServiceFactory.getSocialService(oauthType);
        return service.getUserInfo(accessToken);
    }
}
