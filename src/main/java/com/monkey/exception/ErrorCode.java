package com.monkey.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    E000("존재하지 않는 유저입니다."),
    E100("요청하신 게시글을 찾을 수 없습니다."),
    E200("존재하지 않는 댓글입니다."),
    E500("잘못된 요청입니다."),
    E600("올바르지 않은 토큰입니다."),
    E601("올바르지 않은 토큰 타입입니다."),
    E602("만료된 토큰입니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
