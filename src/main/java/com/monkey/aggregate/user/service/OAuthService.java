package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;

public interface OAuthService {
    OAuthUserInfo getUserInfo(String accessToken);
}
