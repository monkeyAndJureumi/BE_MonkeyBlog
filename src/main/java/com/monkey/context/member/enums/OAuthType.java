package com.monkey.context.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.enums.EntityEnumerable;
import lombok.Getter;

@Getter
public enum OAuthType implements EntityEnumerable {
    KAKAO("카카오"),
    NAVER("네이버");

    private final String description;

    OAuthType(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @JsonCreator
    public static OAuthType create(String value) {
        for (OAuthType social : OAuthType.values()) {
            if (social.name().toLowerCase().equals(value))
                return social;
        }
        return null;
    }
}
