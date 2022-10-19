package com.monkey.context.member.enums;

import com.monkey.enums.ErrorCode;
import lombok.Getter;

@Getter
public enum KakaoError implements ErrorCode {
    KOE001("잘못된 요청"),
    KOE002("잘못된 URL"),
    KOE003("카카오 OAuth 서버 에러"),
    KOE004("카카오 로그인 비활성화");

    private final String description;

    KakaoError(String description) {
        this.description = description;
    }

    public static KakaoError findBy(String name) {
        for (KakaoError error : KakaoError.values()) {
            if (error.name().equals(name))
                return error;
        }
        return null;
    }

    @Override
    public String getCode() {
        return name();
    }
}
