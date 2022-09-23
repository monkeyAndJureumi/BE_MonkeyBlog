package com.monkey.aggregate.user.dto.social;

public abstract class OauthTokenResponseDto {
    public abstract String getTokenType();
    public abstract String getAccessToken();
    public abstract Integer getAccessTokenExpiresIn();
    public abstract String getRefreshToken();
    public abstract Integer getRefreshTokenExpiresIn();
}
