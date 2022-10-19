package com.monkey.context.member.controller;

import com.monkey.context.member.exception.MemberException;
import com.monkey.aop.advice.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@org.springframework.web.bind.annotation.RestControllerAdvice
public class UserRestControllerAdvice {
    @ExceptionHandler(MemberException.class)
    protected ResponseEntity<?> webClientException(MemberException exception) {
        return new ResponseEntity<>(new RestControllerAdvice.ExceptionResponse(exception.getCode(), exception.getMessage()), exception.getHttpStatus());
    }
}
