package com.monkey.aggregate.user.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.exception.WebClientException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserRestControllerAdvice {
    @ExceptionHandler(WebClientException.class)
    protected ResponseEntity<?> webClientException(WebClientException exception) {
        return new ResponseEntity<>(new UserErrorResponse(exception.getErrorCode(), exception.getMessage()), exception.getHttpStatus());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class UserErrorResponse {
        @JsonProperty("error_code")
        private String errorCode;
        @JsonProperty("error_message")
        private String message;

        public UserErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }
    }
}
