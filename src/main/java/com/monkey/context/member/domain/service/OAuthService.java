package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.oauth.OauthTokenResponseDto;

public interface OAuthService {
    OAuthUserInfoDto getUserInfo(String accessToken);
    OauthTokenResponseDto requestAccessToken(String authorizationCode);
}
