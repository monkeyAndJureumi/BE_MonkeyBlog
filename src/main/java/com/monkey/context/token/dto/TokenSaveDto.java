package com.monkey.context.token.dto;

import com.monkey.context.member.domain.MemberId;
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

    public TokenSaveDto(MemberId memberId, JwtProperties jwtProperties) {
        this.accessToken = JwtTokenUtils.CreateAccessToken(jwtProperties.getAccessTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), memberId);
        this.refreshToken = JwtTokenUtils.CreateRefreshToken(jwtProperties.getRefreshTokenExpiration(), jwtProperties.getIssuer(), jwtProperties.getSecretKey(), memberId);
        this.expiration = jwtProperties.getRefreshTokenExpiration();
    }
}
