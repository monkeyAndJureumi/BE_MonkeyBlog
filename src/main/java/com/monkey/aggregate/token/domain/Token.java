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
    private String userCode;

    private UserId userId;

    private String accessToken;

    @Indexed
    private String refreshToken;

    @TimeToLive
    private Long expiration;
    private LocalDateTime issuedAt;

    public Token(TokenSaveDto dto) {
        this.userCode = dto.getUserCode();
        this.userId = dto.getUserId();
        this.accessToken = dto.getAccessToken();
        this.refreshToken = dto.getRefreshToken();
        this.expiration = dto.getExpiration();
        this.issuedAt = LocalDateTime.now();
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserCode() {
        return userCode;
    }

    public UserId getUserId() {
        return userId;
    }

    public Long getExpiration() {
        return expiration;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void refresh(JwtProperties jwtProperties) {
        this.accessToken = JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId.getId(), userCode);
    }
}
