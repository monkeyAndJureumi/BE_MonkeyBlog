package com.monkey.aggregate.user.infra.client.kakao.impl;

import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.aggregate.user.infra.client.kakao.KakaoApiWebClient;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.KakaoProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class KakaoApiWebClientImpl implements KakaoApiWebClient {
    private final KakaoProperties properties;
    private final WebClient client;

    public KakaoApiWebClientImpl(KakaoProperties properties, WebClient kakaoApiClient) {
        this.properties = properties;
        this.client = kakaoApiClient;
    }

    @Override
    public KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters) {
        return client.post()
                .uri(uriBuilder -> uriBuilder.path(properties.getUserInfoUri()).build())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .exchangeToMono(response -> {
                    if (response.statusCode() != HttpStatus.OK)
                        response.createException().doOnError(throwable -> {
                            throw new MonkeyException(MonkeyErrorCode.E500);
                        });
                    return response.bodyToMono(KakaoUserInfoResponseDto.class);
                }).block();
    }
}
