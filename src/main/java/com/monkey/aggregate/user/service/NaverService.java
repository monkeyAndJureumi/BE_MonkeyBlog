package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NaverService implements SocialService {
    @Override
    public UserInfo getUserInfo(OauthToken dto) {
        return null;
    }
}
