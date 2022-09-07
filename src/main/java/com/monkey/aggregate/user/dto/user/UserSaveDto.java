package com.monkey.aggregate.user.dto.user;

import com.monkey.aggregate.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSaveDto {
    private String name;
    private String email;
    private String number;
    private SocialType social;
}
