package com.monkey.context.member.domain.service;

import com.monkey.context.member.enums.OauthType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OauthServiceFactory {
    private final OAuthKakaoServiceImpl OAuthKakaoServiceImpl;
    private final OAuthNaverServiceImpl OAuthNaverServiceImpl;

    public OAuthService getSocialService(OauthType type) {
        switch (type) {
            case KAKAO:
                return OAuthKakaoServiceImpl;
            case NAVER:
                return OAuthNaverServiceImpl;
        }

        throw new IllegalStateException("서버 오류");
    }

}
