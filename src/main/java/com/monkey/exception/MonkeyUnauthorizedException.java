package com.monkey.exception;

public class MonkeyUnauthorizedException extends MonkeyException {
    public MonkeyUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
