package com.monkey.aggregate.user.view;

import com.monkey.aggregate.user.enums.UserSocial;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSaveReq {
    private String name;
    private String email;
    private String number;
    private UserSocial social;
}
