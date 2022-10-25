package com.monkey.context.member.domain.service;

import com.monkey.context.member.enums.OAuthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthServiceFactory {
    private final OAuthKakaoServiceImpl OAuthKakaoServiceImpl;
    private final OAuthNaverServiceImpl OAuthNaverServiceImpl;

    public OAuthService getSocialService(OAuthType type) {
        switch (type) {
            case KAKAO:
                return OAuthKakaoServiceImpl;
            case NAVER:
                return OAuthNaverServiceImpl;
        }

        throw new IllegalStateException("oauth type is not exists");
    }

}
