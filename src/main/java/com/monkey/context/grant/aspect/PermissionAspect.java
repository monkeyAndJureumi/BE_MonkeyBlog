package com.monkey.context.grant.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@RequiredArgsConstructor
public class PermissionAspect {

    @Around("@annotation(PreAuthorize)")
    public void authorize(ProceedingJoinPoint joinPoint) {

    }

}
