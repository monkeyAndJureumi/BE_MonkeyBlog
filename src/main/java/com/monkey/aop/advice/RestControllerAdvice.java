package com.monkey.aop.advice;

import com.monkey.enums.MonkeyErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E400.getCode(), getErrorMessage(exception.getBindingResult().getAllErrors())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionResponse> constraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E400.getCode(), getErrorMessage(exception.getConstraintViolations().iterator())), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionResponse> illegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E400.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ExceptionResponse> signatureException() {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E600.getCode(), MonkeyErrorCode.E600.getDescription()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> expiredJwtException() {
        return new ResponseEntity<>(new ExceptionResponse(MonkeyErrorCode.E602.getCode(), MonkeyErrorCode.E602.getDescription()), HttpStatus.UNAUTHORIZED);
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

    private String getErrorMessage(final List<ObjectError> errors) {
        final StringBuilder result = new StringBuilder();
        errors.forEach(error -> result.append(error.getDefaultMessage()));
        return result.toString();
    }

    private String getErrorMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder result = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            result.append(getPropertyName(constraintViolation.getPropertyPath().toString()));
            result.append(" is ");
            result.append(constraintViolation.getMessage());
        }

        return result.toString();
    }

    private String getPropertyName(String path) {
        return path.substring(path.lastIndexOf(".") + 1);
    }
}
