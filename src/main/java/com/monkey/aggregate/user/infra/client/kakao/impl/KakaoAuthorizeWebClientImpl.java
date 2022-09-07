package com.monkey.aggregate.user.infra.client.kakao.impl;

import com.monkey.aggregate.user.dto.social.kakao.KakaoAuthorizeCodeResponseDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoTokenResponseDto;
import com.monkey.aggregate.user.infra.client.kakao.KakaoAuthorizeWebClient;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.KakaoProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;

@Component
public class KakaoAuthorizeWebClientImpl implements KakaoAuthorizeWebClient {
    private final WebClient client;
    private final KakaoProperties kakaoProperties;

    public KakaoAuthorizeWebClientImpl(WebClient kakaoAuthorizeClient, KakaoProperties kakaoProperties) {
        this.client = kakaoAuthorizeClient;
        this.kakaoProperties = kakaoProperties;
    }

    @Override
    public KakaoAuthorizeCodeResponseDto requestAuthorizeCode(MultiValueMap<String, String> urlParameters) {
        return client
                .get()
                .uri(uriBuilder -> uriBuilder.path("").queryParams(urlParameters).build())
                .exchangeToMono(response -> {
                    if (response.statusCode() != HttpStatus.OK)
                        response.createException().doOnError(throwable -> {
                            throw new MonkeyException(MonkeyErrorCode.E500);
                        });
                    return response.bodyToMono(KakaoAuthorizeCodeResponseDto.class);
                }).block();
    }

    @Override
    public KakaoTokenResponseDto requestToken(MultiValueMap<String, String> urlParameters) {
        return client
                .post()
                .uri(uriBuilder -> uriBuilder.path(kakaoProperties.getTokenUri()).queryParams(urlParameters).build())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .acceptCharset(StandardCharsets.UTF_8)
                .exchangeToMono(response -> {
                    if (response.statusCode() != HttpStatus.OK)
                        response.createException().doOnError(throwable -> {
                            throw new MonkeyException(MonkeyErrorCode.E500);
                        });
                    return response.bodyToMono(KakaoTokenResponseDto.class);
                }).block();
    }
}
