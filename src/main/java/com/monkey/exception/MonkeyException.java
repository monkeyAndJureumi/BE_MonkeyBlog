package com.monkey.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class MonkeyException extends RuntimeException {
    private final ErrorCode errorCode;
    private HttpStatus httpStatus;

    public MonkeyException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public MonkeyException(ErrorCode errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
