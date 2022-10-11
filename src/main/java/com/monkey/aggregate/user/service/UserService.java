package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aggregate.user.enums.OauthType;

public interface UserService {
    void updateProfile(UserId userId, UserProfileUpdateDto dto);
    void deactivateUser(UserId userId);
    UserId getUserIdOrElseCreate(OauthType oauthType, String accessToken);
}
