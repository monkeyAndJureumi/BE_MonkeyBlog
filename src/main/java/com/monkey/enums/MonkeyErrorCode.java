package com.monkey.enums;

import com.monkey.enums.ErrorCode;
import lombok.Getter;

@Getter
public enum MonkeyErrorCode implements ErrorCode {
    E000("존재하지 않는 유저입니다."),
    E001("권한이 없습니다."),
    E100("요청하신 게시글을 찾을 수 없습니다."),
    E200("존재하지 않는 댓글입니다."),
    E400("잘못된 요청입니다."),
    E500("내부 오류"),
    E600("올바르지 않은 토큰입니다."),
    E601("올바르지 않은 토큰 타입입니다."),
    E602("만료된 토큰입니다.");

    private final String description;

    MonkeyErrorCode(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.getDescription();
    }
}
