package com.monkey.context.temp_post;

import com.monkey.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum TempPostErrorCode implements ErrorCode {
    TP404(HttpStatus.NOT_FOUND, "temp post does not exist")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    TempPostErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return name();
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
