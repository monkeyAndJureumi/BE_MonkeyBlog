package com.monkey.exception;

import com.monkey.enums.ErrorCode;
import lombok.Getter;

@Getter
public class MonkeyWebClientException extends RuntimeException {
    private final ErrorCode error;

    public MonkeyWebClientException(ErrorCode error) {
        this.error = error;
    }
}
