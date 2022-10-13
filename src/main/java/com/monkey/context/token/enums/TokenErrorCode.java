package com.monkey.context.token.enums;

import com.monkey.enums.ErrorCode;
import lombok.Getter;

@Getter
public enum TokenErrorCode implements ErrorCode {
    T400("invalid token");

    private final String message;

    TokenErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getDescription() {
        return this.message;
    }
}
