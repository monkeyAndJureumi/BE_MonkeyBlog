package com.monkey.aggregate.token.dto;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenSaveDto {
    private UserId userId;
    private String userCode;
    private String accessToken;
    private String refreshToken;
    private Long expiration;

    public TokenSaveDto(UserId userId, String userCode, JwtProperties jwtProperties) {
        this.userId = userId;
        this.userCode = userCode;
        this.accessToken = "Bearer " + JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId.getId(), userCode);
        this.refreshToken = "Bearer " + JwtTokenUtils.CreateRefreshToken(jwtProperties.getRefreshTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId.getId(), userCode);
        this.expiration = jwtProperties.getRefreshTokenExpiration();
    }
}
