package com.monkey.context.member.infra.client.kakao.impl;

import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.enums.WebClientErrorCode;
import com.monkey.context.member.infra.client.kakao.KakaoApiWebClient;
import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import com.monkey.exception.MonkeyException;
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
    public KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters) {
        log.info("카카오 유저정보 요청");
        KakaoUserInfoResponseDto responseDto = client.post()
                .uri(uriBuilder -> uriBuilder.path(properties.getUserInfoUri()).build())
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .retrieve().onStatus(httpStatus -> httpStatus != HttpStatus.OK,
                        response -> response.createException().flatMap(err -> {
                            log.error(err.getResponseBodyAsString());
                            return Mono.error(new MonkeyException(WebClientErrorCode.findByHttpStatus(err.getStatusCode())));
                        }))
                .bodyToMono(KakaoUserInfoResponseDto.class).block();
        return responseDto;
    }
}
