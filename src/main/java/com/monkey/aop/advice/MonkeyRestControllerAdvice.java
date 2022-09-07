package com.monkey.aop.advice;

import com.monkey.aggregate.user.exception.UserNotFoundException;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MonkeyRestControllerAdvice {
    @ExceptionHandler(MonkeyException.class)
    public ResponseEntity<ExceptionResponse> monkeyException(MonkeyException e) {
        log.error("{}", e.getMessage());
        return new ResponseEntity<>(new ExceptionResponse(e.getErrorCode().getCode(), e.getErrorCode().getDescription()), e.getHttpStatus());
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionResponse> signatureException() {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E600.getCode(), MonkeyErrorCode.E600.getDescription()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> expiredJwtException() {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E602.getCode(), MonkeyErrorCode.E602.getDescription()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getErrorCode().getCode(), e.getErrorCode().getDescription()), HttpStatus.UNAUTHORIZED);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ExceptionResponse {
        private String errorCode;
        private String message;

        private ExceptionResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}
