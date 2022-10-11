package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.enums.OauthType;
import com.monkey.aggregate.user.service.impl.KakaoService;
import com.monkey.aggregate.user.service.impl.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthServiceFactory {
    private final KakaoService kakaoService;
    private final NaverService naverService;

    public OAuthService getSocialService(OauthType type) {
        switch (type) {
            case KAKAO:
                return kakaoService;
            case NAVER:
                return naverService;
        }

        throw new IllegalStateException("서버 오류");
    }

}
