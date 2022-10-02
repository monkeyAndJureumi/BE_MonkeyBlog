package com.monkey.aggregate.user.dto.user;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserIdentityDto {
    private String userCode;
    private UserId userId;

    public UserIdentityDto(User user) {
        this.userCode = user.getCode();
        this.userId = user.getUserId();
    }
}
