package com.monkey.aggregate.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.enums.EntityEnum;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.Getter;

@Getter
public enum SocialType implements EntityEnum {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    SocialType(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static SocialType create(String value) {
        for (SocialType social : SocialType.values()) {
            if (social.name().equalsIgnoreCase(value))
                return social;
        }
        return null;
    }
}
