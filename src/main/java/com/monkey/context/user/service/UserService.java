package com.monkey.context.user.service;

import com.monkey.context.user.domain.UserId;
import com.monkey.context.user.dto.user.UserProfileUpdateDto;
import com.monkey.context.user.enums.OauthType;

public interface UserService {
    void updateProfile(UserId userId, UserProfileUpdateDto dto);
    void deactivateUser(UserId userId);
    UserId getUserIdOrElseCreate(OauthType oauthType, String accessToken);
}
