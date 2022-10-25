package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.oauth.OAuthTokenResponseDto;

public interface OAuthService {
    OAuthUserInfoDto getUserInfo(String accessToken);
    OAuthTokenResponseDto requestAccessToken(String authorizationCode);
}
