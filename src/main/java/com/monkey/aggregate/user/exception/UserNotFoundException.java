package com.monkey.aggregate.user.exception;

import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;

public class UserNotFoundException extends MonkeyException {
    public UserNotFoundException(MonkeyErrorCode errorCode) {
        super(errorCode);
    }
}
