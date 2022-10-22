package com.monkey.context.member.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.dto.oauth.OauthTokenResponseDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoTokenRequestDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoTokenResponseDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoRequestDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.infra.client.kakao.KakaoClient;
import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import com.monkey.utils.WebClientUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class OAuthKakaoServiceImpl implements OAuthService {
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;
    private final KakaoClient kakaoClient;

    public OAuthKakaoServiceImpl(ObjectMapper objectMapper, KakaoProperties properties, KakaoClient kakaoClient) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.kakaoClient = kakaoClient;
    }

    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        KakaoUserInfoRequestDto requestDto = new KakaoUserInfoRequestDto(true, properties.getPropertyKeys());
        MultiValueMap<String, String> parameters = WebClientUtils.convertParameters(requestDto, objectMapper);
        return kakaoClient.requestUserInfo(accessToken, parameters);
    }

    @Override
    public KakaoTokenResponseDto requestAccessToken(String authorizationCode) {
        KakaoTokenRequestDto requestDto = new KakaoTokenRequestDto(authorizationCode, properties);
        MultiValueMap<String, String> parameters = WebClientUtils.convertParameters(requestDto, objectMapper);
        return kakaoClient.requestAccessToken(parameters);
    }
}
