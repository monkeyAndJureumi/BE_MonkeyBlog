package com.monkey.aggregate.user.service.impl;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverService implements OAuthService {

    @Override
    public OAuthUserInfo getUserInfo(String accessToken) {
        return null;
    }
}
