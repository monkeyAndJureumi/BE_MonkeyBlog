package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthNaverServiceImpl implements OAuthService {

    @Override
    public OAuthUserInfo getUserInfo(String accessToken) {
        return null;
    }
}
