package com.monkey.context.user.service;

import com.monkey.context.user.dto.social.OAuthUserInfo;

public interface OAuthService {
    OAuthUserInfo getUserInfo(String accessToken);
}
