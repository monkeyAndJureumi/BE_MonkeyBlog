package com.monkey.context.user.infra.client.kakao.impl;

import com.monkey.context.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.user.infra.client.kakao.KakaoWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoWebClientImpl implements KakaoWebClient {
    private final KakaoApiWebClientImpl apiWebClient;

    @Override
    public KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters) {
        return apiWebClient.requestUserInfo(accessToken, parameters);
    }
}
