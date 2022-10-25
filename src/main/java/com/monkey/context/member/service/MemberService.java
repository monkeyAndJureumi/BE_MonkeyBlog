package com.monkey.context.member.service;

import com.monkey.context.member.domain.Member;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.domain.MemberProfile;
import com.monkey.context.member.domain.service.OAuthService;
import com.monkey.context.member.domain.service.OAuthServiceFactory;
import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.member.MemberProfileUpdateDto;
import com.monkey.context.member.dto.oauth.OAuthTokenResponseDto;
import com.monkey.context.member.enums.OAuthType;
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
//        MemberProfile profile = memberRepository.findProfileByUserId(memberId)
//                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
//        permissionService.checkPermission(memberId, profile);
//        profile.update(dto);
        Member member = memberRepository.findByUserIdAndStatusIsActivate(memberId)
                        .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        member.updateProfile(permissionService, dto);

        log.info("[{}] - Update Profile", memberId.getId());
    }

    @Transactional
    public void deactivateUser(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        permissionService.checkPermission(memberId, member);
        member.deactivate();
        log.info("[{}] - Deactivate", memberId.getId());
    }

    @Transactional
    public MemberId getUserIdOrElseCreate(OAuthType oauthType, String accessToken) {
        OAuthUserInfoDto userInfo = getUserInfo(oauthType, accessToken);
        return getMemberId(oauthType, userInfo);
    }

    @Transactional
    public MemberId getMember(OAuthType oauthType, String authorizationCode) {
        OAuthService service = OAuthServiceFactory.getSocialService(oauthType);
        OAuthTokenResponseDto tokenResponseDto = getResourceAccessToken(service, authorizationCode);
        OAuthUserInfoDto userInfoDto = getResourceUserInfo(service, tokenResponseDto.getAccessToken());
        return getMemberId(oauthType, userInfoDto);
    }
    @Transactional
    public MemberId getMemberId(OAuthType oauthType, OAuthUserInfoDto userInfo) {
        Member result = memberRepository.findById(new MemberId(oauthType + "_" + userInfo.getId()))
                .orElseGet(() -> {
                    Member member = memberRepository.save(new Member(userInfo));
//                    member.setProfile(new MemberProfile(userInfo));
                    log.info("Create User - [{}]", member.getMemberId().getId());
                    return member;
                });
        return result.getMemberId();
    }

    private OAuthTokenResponseDto getResourceAccessToken(OAuthService service, String authorizationCode) {
        return service.requestAccessToken(authorizationCode);
    }

    private OAuthUserInfoDto getResourceUserInfo(OAuthService service, String accessToken) {
        return service.getUserInfo(accessToken);
    }

    @Deprecated
    private OAuthUserInfoDto getUserInfo(OAuthType oauthType, String accessToken) {
        OAuthService service = OAuthServiceFactory.getSocialService(oauthType);
        return service.getUserInfo(accessToken);
    }
}
