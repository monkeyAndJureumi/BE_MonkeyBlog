package com.monkey.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.enums.EntityEnum;
import lombok.Getter;

@Getter
public enum UserSocial implements EntityEnum {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    UserSocial(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static UserSocial create(String value) {
        for (UserSocial social : UserSocial.values()) {
            if (social.name().equals(value))
                return social;
        }
        throw new IllegalArgumentException("올바르지 않은 요청 값 입니다.");
    }
}
