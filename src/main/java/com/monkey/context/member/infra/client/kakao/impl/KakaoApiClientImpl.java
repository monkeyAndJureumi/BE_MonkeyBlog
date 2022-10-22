package com.monkey.context.member.infra.client.kakao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.dto.oauth.kakao.KakaAuthErrorDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoApiErrorDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import com.monkey.context.member.enums.WebClientErrorCode;
import com.monkey.context.member.infra.client.kakao.KakaoApiClient;
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
public class KakaoApiClientImpl implements KakaoApiClient {
    private final ObjectMapper objectMapper;
    private final KakaoProperties properties;
    private final WebClient client;

    public KakaoApiClientImpl(ObjectMapper objectMapper, KakaoProperties properties, WebClient kakaoApiClient) {
        this.objectMapper = objectMapper;
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
                            KakaoApiErrorDto errorDto = convertToErrorDto(err.getStatusCode(), err.getResponseBodyAsString());
                            return Mono.error(new MonkeyException(WebClientErrorCode.findByHttpStatus(err.getStatusCode())));
                        }))
                .bodyToMono(KakaoUserInfoResponseDto.class).block();
        return responseDto;
    }

    private KakaoApiErrorDto convertToErrorDto(HttpStatus httpStatus, String errorBody) {
        try {
            log.error(errorBody);
            KakaoApiErrorDto errorDto = objectMapper.readValue(errorBody, KakaoApiErrorDto.class);
            log.error("httpStatus: {}, message: {}, code: {}",httpStatus, errorDto.getMsg(), errorDto.getCode());
            return errorDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("카카오 에러 메세지 변환 중에 오류가 발생했습니다.");
        }
    }
}
