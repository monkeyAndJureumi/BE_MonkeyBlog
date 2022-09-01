package com.monkey.domain.user.root.view;

import com.monkey.domain.user.root.enums.UserSocial;
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
