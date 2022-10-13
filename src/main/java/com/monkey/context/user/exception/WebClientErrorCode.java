package com.monkey.context.user.exception;

import com.monkey.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum WebClientErrorCode implements ErrorCode {
    U000(HttpStatus.INTERNAL_SERVER_ERROR, "정의되지 않은 에러"),
    U400(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    U401(HttpStatus.UNAUTHORIZED, "인증되지 않는 요청입니다.");

    private final HttpStatus httpStatus;
    private final String description;

    WebClientErrorCode(HttpStatus httpStatus, String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static WebClientErrorCode findByHttpStatus(HttpStatus httpStatus) {
        for(WebClientErrorCode code : WebClientErrorCode.values()) {
            if (code.httpStatus.equals(httpStatus))
                return code;
        }

        return WebClientErrorCode.U000;
    }
}
