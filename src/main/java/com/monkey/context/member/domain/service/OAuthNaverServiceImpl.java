package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.oauth.OauthTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthNaverServiceImpl implements OAuthService {

    @Override
    public OAuthUserInfoDto getUserInfo(String accessToken) {
        return null;
    }

    @Override
    public OauthTokenResponseDto requestAccessToken(String authorizationCode) {
        return null;
    }
}
