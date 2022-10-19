package com.monkey.context.member.domain.service;

import com.monkey.context.member.enums.OauthType;
import com.monkey.context.member.exception.MemberErrorCode;
import com.monkey.context.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthServiceFactory {
    private final OAuthKakaoServiceImpl OAuthKakaoServiceImpl;
    private final OAuthNaverServiceImpl OAuthNaverServiceImpl;

    public OAuthService getSocialService(OauthType type) {
        switch (type) {
            case KAKAO:
                return OAuthKakaoServiceImpl;
            case NAVER:
                return OAuthNaverServiceImpl;
        }

        throw new MemberException(MemberErrorCode.M400);
    }

}
