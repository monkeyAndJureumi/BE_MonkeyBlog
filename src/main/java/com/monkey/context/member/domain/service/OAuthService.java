package com.monkey.context.member.domain.service;

import com.monkey.context.member.dto.oauth.OAuthUserInfo;

public interface OAuthService {
    OAuthUserInfo getUserInfo(String accessToken);
}
