package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.user.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SocialServiceFactory {
    private final KakaoService kakaoService;
    private final NaverService naverService;

    public SocialService getSocialService(UserLoginDto dto) {
        switch (dto.getSocial()) {
            case KAKAO:
                return kakaoService;
            case NAVER:
                return naverService;
        }

        throw new IllegalStateException("해당 소셜타입은 지원하지 않습니다.");
    }

}
