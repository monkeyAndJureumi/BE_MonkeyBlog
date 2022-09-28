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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E400.getCode(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MonkeyException.class)
    public ResponseEntity<ExceptionResponse> monkeyException(MonkeyException e) {
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
    public static class ExceptionResponse {
        private String errorCode;
        private String message;

        public ExceptionResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}
