package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.enums.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialServiceFactory {
    private final KakaoService kakaoService;
    private final NaverService naverService;

    public SocialService getSocialService(SocialType type) {
        switch (type) {
            case KAKAO:
                return kakaoService;
            case NAVER:
                return naverService;
        }

        throw new IllegalStateException("서버 오류");
    }

}
