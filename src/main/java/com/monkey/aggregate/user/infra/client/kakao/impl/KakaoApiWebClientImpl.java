package com.monkey.aggregate.user.infra.client.kakao.impl;

import com.monkey.aggregate.user.dto.social.OAuthToken;
import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoResponseDto;
import com.monkey.aggregate.user.exception.WebClientErrorCode;
import com.monkey.aggregate.user.exception.WebClientException;
import com.monkey.aggregate.user.infra.client.kakao.KakaoApiWebClient;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.KakaoProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KakaoApiWebClientImpl implements KakaoApiWebClient {
    private final KakaoProperties properties;
    private final WebClient client;

    public KakaoApiWebClientImpl(KakaoProperties properties, WebClient kakaoApiClient) {
        this.properties = properties;
        this.client = kakaoApiClient;
    }

    @Override
    public KakaoUserInfoResponseDto requestUserInfo(OAuthToken dto, MultiValueMap<String, String> parameters) {
        KakaoUserInfoResponseDto responseDto = client.post()
                .uri(uriBuilder -> uriBuilder.path(properties.getUserInfoUri()).build())
                .header(HttpHeaders.AUTHORIZATION, dto.getAccessToken())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .retrieve().onStatus(httpStatus -> httpStatus != HttpStatus.OK,
                        response -> response.createException().flatMap(err -> Mono.error(new WebClientException(err.getResponseBodyAsString(), err.getStatusCode()))))
                .bodyToMono(KakaoUserInfoResponseDto.class).block();
        return responseDto;
    }
}
