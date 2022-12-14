package com.monkey.context.member.config;

import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final KakaoProperties kakaoProperties;

    @Bean
    public WebClient kakaoAuthClient() {
        return WebClient.builder()
                .baseUrl(kakaoProperties.getAuthorizeBaseUrl())
                .build();
    }

    @Bean
    public WebClient kakaoApiClient() {
        return WebClient.builder()
                .baseUrl(kakaoProperties.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION)
                .build();
    }
}
