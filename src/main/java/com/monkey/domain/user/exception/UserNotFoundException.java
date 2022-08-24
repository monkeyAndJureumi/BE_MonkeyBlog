package com.monkey.domain.user.exception;

import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;

public class UserNotFoundException extends MonkeyException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserNotFoundException(ErrorCode errorCode, String internalMessage) {
        super(errorCode, internalMessage);
    }
}
