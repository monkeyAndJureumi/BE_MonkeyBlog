package com.monkey.context.member.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoRequestDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.infra.client.kakao.KakaoWebClient;
import com.monkey.properties.KakaoProperties;
import com.monkey.utils.WebClientUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class OAuthKakaoServiceImpl implements OAuthService {
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;
    private final KakaoWebClient kakaoWebClient;

    public OAuthKakaoServiceImpl(ObjectMapper objectMapper, KakaoProperties properties, KakaoWebClient kakaoWebClient) {
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
