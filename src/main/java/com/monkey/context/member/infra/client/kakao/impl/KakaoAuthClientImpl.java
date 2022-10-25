package com.monkey.context.member.infra.client.kakao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.dto.oauth.kakao.KakaoAuthErrorDto;
import com.monkey.context.member.dto.oauth.kakao.KakaoTokenResponseDto;
import com.monkey.context.member.enums.WebClientErrorCode;
import com.monkey.context.member.infra.client.kakao.KakaoAuthClient;
import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import com.monkey.exception.MonkeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KakaoAuthClientImpl implements KakaoAuthClient {
    private final ObjectMapper objectMapper;
    private final KakaoProperties kakaoProperties;
    private final WebClient client;

    public KakaoAuthClientImpl(ObjectMapper objectMapper, KakaoProperties kakaoProperties, WebClient kakaoAuthClient) {
        this.objectMapper = objectMapper;
        this.kakaoProperties = kakaoProperties;
        this.client = kakaoAuthClient;
    }

    @Override
    public KakaoTokenResponseDto requestAccessToken(MultiValueMap<String, String> parameters) {
        log.info("카카오 액세스토큰 요청");
        return client.post()
                .uri(uriBuilder -> uriBuilder.path(kakaoProperties.getTokenUri()).build())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(parameters))
                .retrieve().onStatus(httpStatus -> httpStatus != HttpStatus.OK,
                        response -> response.createException().flatMap(err -> {
                            KakaoAuthErrorDto errorDto = convertToErrorDto(err.getStatusCode(), err.getResponseBodyAsString());
                            return Mono.error(new MonkeyException(WebClientErrorCode.findByHttpStatus(err.getStatusCode())));
                        }))
                .bodyToMono(KakaoTokenResponseDto.class).block();
    }

    private KakaoAuthErrorDto convertToErrorDto(HttpStatus httpStatus, String errorBody) {
        try {
            KakaoAuthErrorDto errorDto = objectMapper.readValue(errorBody, KakaoAuthErrorDto.class);
            log.error("{}, error: {}, message: {}, code: {}", httpStatus, errorDto.getError(), errorDto.getDescription(), errorDto.getCode());
            return errorDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException("카카오 에러 메세지 변환 중에 오류가 발생했습니다.");
        }
    }
}
