package com.monkey.context.token.enums;

import com.monkey.enums.ErrorCode;
import org.springframework.http.HttpStatus;

public enum TokenErrorCode implements ErrorCode {
    T400("invalid token", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;

    TokenErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
