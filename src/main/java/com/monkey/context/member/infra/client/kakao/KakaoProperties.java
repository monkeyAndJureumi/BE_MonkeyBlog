package com.monkey.context.member.infra.client.kakao;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Getter
@Configuration
@PropertySource("classpath:properties/${spring.profiles.active}/kakao.properties")
public class KakaoProperties {
    @Value("${authorize_url}")
    private String authorizeBaseUrl;

    @Value("${api_url}")
    private String apiUrl;

    @Value("${token_uri}")
    private String tokenUri;

    @Value("${user_info_uri}")
    private String userInfoUri;

    @Value("${client_id}")
    private String clientId;

    @Value("${redirect_uri}")
    private String redirectUri;

    @Value("${client_secret}")
    private String clientSecret;

    @Value("${user_info.property_keys}")
    private List<String> propertyKeys;
}
