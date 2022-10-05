package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverService implements SocialService {
    @Override
    public OAuthUserInfo getUserInfo(OauthToken dto) {
        return null;
    }
}
