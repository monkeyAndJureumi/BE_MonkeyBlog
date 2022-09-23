package com.monkey.aggregate.user.dto.token;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponseDto {
    private String tokenType;
    private String accessToken;
    private Integer accessTokenExpiresIn;
    private String refreshToken;
    private Integer refreshTokenExpiresIn;

    public TokenResponseDto(String accessToken, Integer accessTokenExpiresIn, String refreshToken, Integer refreshTokenExpiresIn) {
        this.tokenType = "Bearer";
        this.accessToken = accessToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }
}
