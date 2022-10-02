package com.monkey.aggregate.token.enums;

import lombok.Getter;

@Getter
public enum TokenErrorCode {
    T400("invalid token");

    private final String message;

    TokenErrorCode(String message) {
        this.message = message;
    }
}
