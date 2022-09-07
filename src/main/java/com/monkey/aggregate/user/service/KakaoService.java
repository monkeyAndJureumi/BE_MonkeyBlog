package com.monkey.aggregate.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.aggregate.user.dto.social.kakao.KakaoTokenResponseDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoUrlParameterDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoRequestDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.aggregate.user.infra.client.kakao.KakaoWebClient;
import com.monkey.properties.KakaoProperties;
import com.monkey.utils.WebClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class KakaoService implements SocialService {
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;
    private final KakaoWebClient kakaoWebClient;

    public KakaoService(ObjectMapper objectMapper, KakaoProperties properties, KakaoWebClient kakaoWebClient) {
        this.objectMapper = objectMapper;
        this.properties = properties;
        this.kakaoWebClient = kakaoWebClient;
    }

    public KakaoTokenResponseDto getTokenDto(String authorizeCode) {
        KakaoUrlParameterDto parameter = new KakaoUrlParameterDto(properties.getGrantType(), properties.getClientId(), properties.getRedirectUri(), authorizeCode, properties.getClientSecret());
        MultiValueMap<String, String> parameters = WebClientUtils.convertParameters(parameter, objectMapper);
        return kakaoWebClient.requestToken(parameters);
    }

    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        KakaoUserInfoRequestDto requestDto = new KakaoUserInfoRequestDto(true, properties.getPropertyKeys());
        MultiValueMap<String, String> parameters = WebClientUtils.convertParameters(requestDto, objectMapper);
        return kakaoWebClient.requestUserInfo(accessToken, parameters);
    }
}
