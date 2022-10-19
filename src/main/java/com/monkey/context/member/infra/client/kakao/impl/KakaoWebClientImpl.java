package com.monkey.context.member.infra.client.kakao.impl;

import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.infra.client.kakao.KakaoWebClient;
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
