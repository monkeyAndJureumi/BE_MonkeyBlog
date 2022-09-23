package com.monkey.aop.advice;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import com.monkey.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
@RequiredArgsConstructor
public class MonkeyRequestBodyAdvice implements RequestBodyAdvice {
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            return UserSessionDto.class.isAssignableFrom(Class.forName(targetType.getTypeName()));
        } catch (ClassNotFoundException e) {
            throw new MonkeyException(MonkeyErrorCode.E400);
        }
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        final UserSessionDto session = (UserSessionDto) body;
        HttpHeaders headers = inputMessage.getHeaders();
        String token = headers.getFirst("Authorization");

        Long id = JwtTokenUtils.ParseJwtToken(token, jwtProperties.getSecretKey());
        User user = userRepository.findById(id).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E000, HttpStatus.UNAUTHORIZED));

        session.setSession(user.getId());

        return session;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
