package com.monkey.context.member.controller;

import com.monkey.context.member.exception.WebClientException;
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
