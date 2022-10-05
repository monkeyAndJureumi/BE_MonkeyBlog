package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.OAuthUserInfo;

public interface SocialService {
    OAuthUserInfo getUserInfo(OauthToken dto);
}
