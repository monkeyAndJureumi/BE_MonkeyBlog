package com.monkey.enums;

import org.springframework.http.HttpStatus;

public interface ErrorCodeEnumerable {
    String getMessage();
    String getCode();
    HttpStatus getHttpStatus();
}
