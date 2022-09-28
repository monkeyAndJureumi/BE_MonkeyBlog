package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.exception.WebClientException;
import com.monkey.aop.advice.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class UserRestControllerAdvice {
    @ExceptionHandler(WebClientException.class)
    protected ResponseEntity<?> webClientException(WebClientException exception) {
        return new ResponseEntity<>(new RestControllerAdvice.ExceptionResponse(exception.getErrorCode(), exception.getMessage()), exception.getHttpStatus());
    }
}
