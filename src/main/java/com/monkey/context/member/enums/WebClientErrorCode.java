package com.monkey.context.member.enums;

import com.monkey.enums.ErrorCodeEnumerable;
import org.springframework.http.HttpStatus;

public enum WebClientErrorCode implements ErrorCodeEnumerable {
    W400(HttpStatus.BAD_REQUEST, "Bad Request"),
    W401(HttpStatus.UNAUTHORIZED, "Bad Credential"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

    WebClientErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static WebClientErrorCode findByHttpStatus(HttpStatus httpStatus) {
        for(WebClientErrorCode code : WebClientErrorCode.values()) {
            if (code.httpStatus.equals(httpStatus))
                return code;
        }

        throw new IllegalStateException();
    }
}
