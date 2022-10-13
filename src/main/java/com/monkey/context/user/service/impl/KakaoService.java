package com.monkey.context.user.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.user.dto.social.kakao.KakaoUserInfoRequestDto;
import com.monkey.context.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.user.infra.client.kakao.KakaoWebClient;
import com.monkey.context.user.service.OAuthService;
import com.monkey.properties.KakaoProperties;
import com.monkey.utils.WebClientUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class KakaoService implements OAuthService {
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;
    private final KakaoWebClient kakaoWebClient;

    public KakaoService(ObjectMapper objectMapper, KakaoProperties properties, KakaoWebClient kakaoWebClient) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.kakaoWebClient = kakaoWebClient;
    }

    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        KakaoUserInfoRequestDto requestDto = new KakaoUserInfoRequestDto(true, properties.getPropertyKeys());
        MultiValueMap<String, String> parameters = WebClientUtils.convertParameters(requestDto, objectMapper);
        return kakaoWebClient.requestUserInfo(accessToken, parameters);
    }
}
