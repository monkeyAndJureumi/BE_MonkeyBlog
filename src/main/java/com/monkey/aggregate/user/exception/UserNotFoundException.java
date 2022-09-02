package com.monkey.aggregate.user.exception;

import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;

public class UserNotFoundException extends MonkeyException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
