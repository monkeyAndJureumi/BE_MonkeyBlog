package com.monkey.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonErrorCode implements ErrorCode {
    E400("잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    E401("권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    E404("요청하신 내용을 찾을 수 없습니다.", HttpStatus.NOT_FOUND)
    ;
    private final String description;
    private final HttpStatus httpStatus;

    CommonErrorCode(String description, HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return description;
    }
}
