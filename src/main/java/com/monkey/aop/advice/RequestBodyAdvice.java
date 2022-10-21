package com.monkey.aop.advice;

import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.context.token.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import com.monkey.dto.UserSessionDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Type;

@RestControllerAdvice
@RequiredArgsConstructor
public class RequestBodyAdvice implements org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice {
    private final JwtProperties jwtProperties;
    private final String AUTH_HEADER = "Authorization";

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            return UserSessionDto.class.isAssignableFrom(Class.forName(targetType.getTypeName()));
        } catch (ClassNotFoundException e) {
            throw new MonkeyException(CommonErrorCode.E400);
        }
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        final UserSessionDto session = (UserSessionDto) body;
        HttpHeaders headers = inputMessage.getHeaders();
        String token = headers.getFirst(AUTH_HEADER);

        Claims claims = JwtTokenUtils.ParseJwtToken(token, jwtProperties.getSecretKey());

        session.setSession(claims.get("user_id", String.class));

        return session;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
