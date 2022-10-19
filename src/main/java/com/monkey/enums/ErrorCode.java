package com.monkey.enums;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getMessage();
    String getCode();
    HttpStatus getHttpStatus();
}
