package com.monkey.aggregate.user.exception;

import org.springframework.http.HttpStatus;

public class WebClientException extends RuntimeException {
    private WebClientErrorCode errorCode;
    private HttpStatus httpStatus;

    public WebClientException(String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = WebClientErrorCode.findByHttpStatus(httpStatus);
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode.getCode();
    }

    public String getMessage() {
        return errorCode.getDescription();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
