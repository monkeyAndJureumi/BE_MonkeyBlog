package com.monkey.context.user.dto.user;

import com.monkey.context.user.domain.User;
import com.monkey.context.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserIdentityDto {
    private UserId userId;

    public UserIdentityDto(User user) {
        this.userId = user.getUserId();
    }
}
