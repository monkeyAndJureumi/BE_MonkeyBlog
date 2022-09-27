package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.UserInfo;

public interface SocialService {
    UserInfo getUserInfo(OauthToken dto);
}
