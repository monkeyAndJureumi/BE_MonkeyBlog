package com.monkey.aggregate.user.infra.repository.custom;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.domain.UserProfile;

import java.util.Optional;

public interface UserCustomRepository {
    Optional<User> findByUserIdAndStatusIsActivate(UserId userId);
    Optional<UserProfile> findProfileByUserId(UserId userId);
}
