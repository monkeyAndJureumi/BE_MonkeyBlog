package com.monkey.aggregate.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GrantType {
    ACCESS_TOKEN("authorization_token"),
    REFRESH_TOKEN("refresh_token");

    private final String description;

    GrantType(String description) {
        this.description = description;
    }

    @JsonCreator
    public static GrantType create(String value) {
        for (GrantType type : GrantType.values()) {
            if (type.name().equalsIgnoreCase(value))
                return type;
        }

        return null;
    }
}
