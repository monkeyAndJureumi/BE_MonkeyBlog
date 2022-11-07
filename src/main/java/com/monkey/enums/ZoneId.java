package com.monkey.enums;

import lombok.Getter;

@Getter
public enum ZoneId {
    SEOUL("+09:00");

    private final String offset;

    ZoneId(String offset) {
        this.offset = offset;
    }
}
