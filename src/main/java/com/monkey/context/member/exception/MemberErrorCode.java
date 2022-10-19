package com.monkey.context.member.exception;

import com.monkey.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MemberErrorCode implements ErrorCode {
    M000(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),
    M400(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    M401(HttpStatus.UNAUTHORIZED, "인증되지 않는 요청입니다.");

    private final HttpStatus httpStatus;
    private final String description;

    MemberErrorCode(HttpStatus httpStatus, String description) {
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

    public static MemberErrorCode findByHttpStatus(HttpStatus httpStatus) {
        for(MemberErrorCode code : MemberErrorCode.values()) {
            if (code.httpStatus.equals(httpStatus))
                return code;
        }

        return MemberErrorCode.M000;
    }
}
