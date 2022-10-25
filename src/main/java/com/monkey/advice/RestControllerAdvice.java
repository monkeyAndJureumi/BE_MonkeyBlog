package com.monkey.advice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.enums.CommonErrorCode;
import com.monkey.enums.ErrorCodeEnumerable;
import com.monkey.exception.MonkeyException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> exception(Exception e) {
        log.error("{}", e.getMessage());
        return new ExceptionResponseEntityWrapper("500", "내부 오류", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ExceptionResponseEntityWrapper("400", exception.getAllErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ExceptionResponse> missingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return new ExceptionResponseEntityWrapper("400", exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionResponse> constraintViolationException(ConstraintViolationException exception) {
        return new ExceptionResponseEntityWrapper("400", exception.getConstraintViolations().iterator(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MonkeyException.class)
    protected ResponseEntity<ExceptionResponse> monkeyException(MonkeyException exception) {
        return new ExceptionResponseEntityWrapper(exception.getCode(), exception.getMessage(), exception.getHttpStatus());
    }

    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class})
    public ResponseEntity<ExceptionResponse> jwtException() {
        return new ExceptionResponseEntityWrapper(CommonErrorCode.E401);
    }

    private static class ExceptionResponseEntityWrapper extends ResponseEntity<ExceptionResponse> {
        public ExceptionResponseEntityWrapper(ErrorCodeEnumerable errorCode) {
            super(new ExceptionResponse(errorCode.getCode(), errorCode.getMessage()), errorCode.getHttpStatus());
        }

        public ExceptionResponseEntityWrapper(String code, String message, HttpStatus status) {
            super(new ExceptionResponse(code, message), status);
        }

        public ExceptionResponseEntityWrapper(String code, final List<ObjectError> errors, HttpStatus status) {
            super(new ExceptionResponse(code, errors), status);
        }

        public ExceptionResponseEntityWrapper(String code, final Iterator<ConstraintViolation<?>> violationIterator, HttpStatus status) {
            super(new ExceptionResponse(code, violationIterator), status);
        }
    }

    private static class ExceptionResponse {
        @JsonProperty("code")
        private String code;

        public String getCode() {
            return code;
        }

        @JsonProperty("message")
        private String message;

        public String getMessage() {
            return message;
        }

        private ExceptionResponse() {}

        public ExceptionResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public ExceptionResponse(String code, final List<ObjectError> errors) {
            this.code = code;
            this.message = getErrorMessage(errors);
        }

        public ExceptionResponse(String code, final Iterator<ConstraintViolation<?>> violationIterator) {
            this.code = code;
            this.message = getErrorMessage(violationIterator);
        }

        private String getErrorMessage(final List<ObjectError> errors) {
            return errors.stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining());
        }

        private String getErrorMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
            final StringBuilder internalMsg = new StringBuilder();
            final StringBuilder result = new StringBuilder();
            while (violationIterator.hasNext()) {
                final ConstraintViolation<?> constraintViolation = violationIterator.next();
                internalMsg.append(getPropertyName(constraintViolation.getPropertyPath().toString()) + " is " + constraintViolation.getMessage());
                result.append(constraintViolation.getMessage());
            }
            log.info("{}", internalMsg);
            return result.toString();
        }

        private String getPropertyName(String path) {
            return path.substring(path.lastIndexOf(".") + 1);
        }
    }
}
