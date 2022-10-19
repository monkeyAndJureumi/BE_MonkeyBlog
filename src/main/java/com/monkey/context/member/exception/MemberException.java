package com.monkey.context.member.exception;

import org.springframework.http.HttpStatus;

public class MemberException extends RuntimeException {
    private MemberErrorCode errorCode;

    public MemberException(MemberErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public MemberException(String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = MemberErrorCode.findByHttpStatus(httpStatus);
    }

    public String getCode() {
        return errorCode.getCode();
    }

    public String getMessage() {
        return errorCode.getDescription();
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }
}
