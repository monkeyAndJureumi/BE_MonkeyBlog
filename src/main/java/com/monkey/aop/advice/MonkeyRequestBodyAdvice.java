package com.monkey.aop.advice;

import com.monkey.domain.user.root.entity.User;
import com.monkey.domain.user.root.repository.UserRepository;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.exception.MonkeyUnauthorizedException;
import com.monkey.utils.TokenUtils;
import com.monkey.view.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@RestControllerAdvice
@RequiredArgsConstructor
public class MonkeyRequestBodyAdvice implements RequestBodyAdvice {
    private final UserRepository userRepository;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            return UserSession.class.isAssignableFrom(Class.forName(targetType.getTypeName()));
        } catch (ClassNotFoundException e) {
            throw new MonkeyException(ErrorCode.E400);
        }
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        final UserSession session = (UserSession) body;
        HttpHeaders headers = inputMessage.getHeaders();
        String token = headers.getFirst("Authorization");

        Long id = TokenUtils.ParseJwtToken(token);
        User user = userRepository.findById(id).orElseThrow(() -> new MonkeyUnauthorizedException(ErrorCode.E000));

        session.setUserId(user.getId());
        session.setUuid(user.getUuid());

        return session;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return null;
    }
}
