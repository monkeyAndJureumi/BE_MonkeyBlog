package com.monkey.aop.advice;

import com.monkey.domain.user.root.exception.UserNotFoundException;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.exception.MonkeyUnauthorizedException;
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
        return new ResponseEntity<>(new ExceptionResponse(e.getErrorCode(), e.getErrorCode().getMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(MonkeyUnauthorizedException.class)
    public ResponseEntity<ExceptionResponse> unauthorizedException(MonkeyUnauthorizedException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getErrorCode(), e.getErrorCode().getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionResponse> signatureException() {
        return new ResponseEntity<>(new ExceptionResponse(ErrorCode.E600, ErrorCode.E600.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> expiredJwtException() {
        return new ResponseEntity<>(new ExceptionResponse(ErrorCode.E602, ErrorCode.E602.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(e.getErrorCode(), e.getErrorCode().getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class ExceptionResponse {
        private ErrorCode errorCode;
        private String message;

        private ExceptionResponse(ErrorCode errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}
