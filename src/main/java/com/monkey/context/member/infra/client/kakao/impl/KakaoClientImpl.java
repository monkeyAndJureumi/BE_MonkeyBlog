package com.monkey.context.member.infra.client.kakao.impl;

import com.monkey.context.member.dto.oauth.kakao.KakaoTokenResponseDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.infra.client.kakao.KakaoAuthClient;
import com.monkey.context.member.infra.client.kakao.KakaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoClientImpl implements KakaoClient {
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoApiClientImpl kakaoApiClient;

    @Override
    public KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters) {
        return kakaoApiClient.requestUserInfo(accessToken, parameters);
    }

    @Override
    public KakaoTokenResponseDto requestAccessToken(MultiValueMap<String, String> parameters) {
        return kakaoAuthClient.requestAccessToken(parameters);
    }
}
