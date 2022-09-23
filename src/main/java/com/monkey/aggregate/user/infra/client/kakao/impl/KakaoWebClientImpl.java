package com.monkey.aggregate.user.infra.client.kakao.impl;

import com.monkey.aggregate.user.dto.social.OAuthToken;
import com.monkey.aggregate.user.dto.social.kakao.KakaoAuthorizeCodeResponseDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoTokenResponseDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.aggregate.user.infra.client.kakao.KakaoWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoWebClientImpl implements KakaoWebClient {
    private final KakaoApiWebClientImpl apiWebClient;

    @Override
    public KakaoUserInfoResponseDto requestUserInfo(OAuthToken dto, MultiValueMap<String, String> parameters) {
        return apiWebClient.requestUserInfo(dto, parameters);
    }
}
