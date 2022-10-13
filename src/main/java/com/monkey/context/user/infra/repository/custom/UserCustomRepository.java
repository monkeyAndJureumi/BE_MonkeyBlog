package com.monkey.context.user.infra.repository.custom;

import com.monkey.context.user.domain.User;
import com.monkey.context.user.domain.UserId;
import com.monkey.context.user.domain.UserProfile;

import java.util.Optional;

public interface UserCustomRepository {
    Optional<User> findByUserIdAndStatusIsActivate(UserId userId);
    Optional<UserProfile> findProfileByUserId(UserId userId);
}
