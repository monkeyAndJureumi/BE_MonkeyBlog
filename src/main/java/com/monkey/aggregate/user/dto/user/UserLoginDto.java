package com.monkey.aggregate.user.dto.user;

import com.monkey.aggregate.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLoginDto {
    private SocialType social;
    private String authorizeCode;
}
