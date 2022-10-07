package com.monkey.aggregate.token.domain;

import com.monkey.aggregate.token.dto.TokenSaveDto;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "jwt_token")
public class Token {
    @Id
    private String refreshToken;

    @TimeToLive
    private Long expiration;

    public Token(TokenSaveDto dto) {
        this.refreshToken = dto.getRefreshToken();
        this.expiration = dto.getExpiration();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String refresh(JwtProperties jwtProperties) {
        return JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), getUserId(jwtProperties));
    }

    public UserId getUserId(JwtProperties jwtProperties) {
        String userId = JwtTokenUtils.ParseJwtToken(refreshToken, jwtProperties.getSecretKey()).get("userId", String.class);
        return new UserId(userId);
    }
}
