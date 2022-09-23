package com.monkey.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:properties/${spring.profiles.active}/token.properties")
public class JwtProperties {
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.refresh_token_expiration}")
    private Long refreshTokenExpiration;

    @Value("${jwt.access_token_expiration}")
    private Long accessTokenExpiration;
}
