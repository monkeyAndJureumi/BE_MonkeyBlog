package com.monkey.exception;

import com.monkey.enums.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@NoArgsConstructor
public class MonkeyException extends RuntimeException {
    private String message;
    private String code;
    private HttpStatus httpStatus;

    public MonkeyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public MonkeyException(String message, ErrorCode errorCode) {
        super(message);
        this.message = message;
        this.code = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
    }

    public MonkeyException(Throwable throwable) {
        super(throwable);
    }
}
