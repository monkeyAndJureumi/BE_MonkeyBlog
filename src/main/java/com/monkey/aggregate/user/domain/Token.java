package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "user_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    private UserId userId;

    private String accessToken;

    private Integer accessTokenExpiration;

    private String refreshToken;

    private Integer refreshTokenExpiration;

    private Boolean isExpired;

    private LocalDateTime issuedAt;

    @Builder
    public Token(UserId userId, JwtProperties jwtProperties) {
        this.userId = userId;
        this.accessToken = JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId.getId());
        this.accessTokenExpiration = jwtProperties.getAccessTokenExpiration().intValue();
        this.refreshToken = JwtTokenUtils.CreateRefreshToken(jwtProperties.getRefreshTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId.getId());
        this.refreshTokenExpiration = jwtProperties.getRefreshTokenExpiration().intValue();
        this.issuedAt = LocalDateTime.now();
        this.isExpired = false;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Integer getAccessTokenExpiration() {
        return accessTokenExpiration;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Integer getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }


    public void refreshAccessToken(Long userId, String refreshToken, JwtProperties jwtProperties) {
        validExpiration();
        if (!this.refreshToken.equals(refreshToken))
            throw new MonkeyException(MonkeyErrorCode.E600);
        this.accessToken = JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId);
    }

    /**
     * 액세스토큰이 만료되기 전에 갱신요청이 들어오거나 리프레쉬 토큰이 만료되었을 경우 expired 필드 값을 false 로 변경
     */
    private void validExpiration() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireAccessTokenDateTime = this.issuedAt.plusSeconds(accessTokenExpiration.longValue());
        LocalDateTime expireRefreshTokenDateTime = this.issuedAt.plusSeconds(refreshTokenExpiration.longValue());

        // 액세스 토큰이 만료되기 전에 갱신 요청이 들어오면 만료처리, 리프레시 토크 만료 확인
        if (now.isBefore(expireAccessTokenDateTime) || now.isAfter(expireRefreshTokenDateTime))
            this.isExpired = true;

        if (this.isExpired)
            throw new MonkeyException(MonkeyErrorCode.E602);
    }
}
