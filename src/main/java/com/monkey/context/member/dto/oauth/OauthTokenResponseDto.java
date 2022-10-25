package com.monkey.context.member.dto.oauth;

public abstract class OAuthTokenResponseDto {
    public abstract String getTokenType();
    public abstract String getAccessToken();
    public abstract Integer getAccessTokenExpiresIn();
    public abstract String getRefreshToken();
    public abstract Integer getRefreshTokenExpiresIn();
}
