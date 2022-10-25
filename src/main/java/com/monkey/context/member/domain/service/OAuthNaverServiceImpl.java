package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.oauth.OAuthTokenResponseDto;
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
    public OAuthTokenResponseDto requestAccessToken(String authorizationCode) {
        return null;
    }
}
