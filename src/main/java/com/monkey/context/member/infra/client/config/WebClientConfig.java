package com.monkey.context.member.infra.client.config;

import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {
    private final KakaoProperties kakaoProperties;

    @Bean
    public WebClient kakaoAuthClient() throws SSLException {
        return WebClient.builder()
                .clientConnector(createReactorHttpConnector())
                .baseUrl(kakaoProperties.getAuthorizeBaseUrl())
                .build();
    }

    @Bean
    public WebClient kakaoApiClient() throws SSLException {
        return WebClient.builder()
                .clientConnector(createReactorHttpConnector())
                .baseUrl(kakaoProperties.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION)
                .build();
    }

    @Deprecated
    private ReactorClientHttpConnector createReactorHttpConnector() throws SSLException {
        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));
        return new ReactorClientHttpConnector(httpClient);
    }
}
