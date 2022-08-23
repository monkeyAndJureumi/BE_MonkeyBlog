package com.monkey.exception;

import lombok.Getter;

@Getter
public class MonkeyException extends RuntimeException {
    private final ErrorCode errorCode;

    public MonkeyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MonkeyException(ErrorCode errorCode, String internalMessage) {
        super(internalMessage);
        this.errorCode = errorCode;
    }
}
