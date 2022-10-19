package com.monkey.context.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.enums.EntityEnum;
import lombok.Getter;

@Getter
public enum OauthType implements EntityEnum {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    OauthType(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static OauthType create(String value) {
        for (OauthType social : OauthType.values()) {
            if (social.name().equalsIgnoreCase(value))
                return social;
        }
        return null;
    }
}
