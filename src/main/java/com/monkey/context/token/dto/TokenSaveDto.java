package com.monkey.context.token.dto;

import com.monkey.context.user.domain.UserId;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenSaveDto {
    private String accessToken;
    private String refreshToken;
    private Long expiration;

    public TokenSaveDto(UserId userId, JwtProperties jwtProperties) {
        this.accessToken = "Bearer " + JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId);
        this.refreshToken = "Bearer " + JwtTokenUtils.CreateRefreshToken(jwtProperties.getRefreshTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), userId);
        this.expiration = jwtProperties.getRefreshTokenExpiration();
    }
}
