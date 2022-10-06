package com.monkey.aop.resolver;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.annotation.NonRequiredParam;
import com.monkey.properties.JwtProperties;
import com.monkey.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


@Component
@RequiredArgsConstructor
public class UserSessionArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtProperties jwtProperties;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserId.class) && !parameter.hasParameterAnnotation(NonRequiredParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String token = httpRequest.getHeader("Authorization");

//        if (token == null && parameter.hasParameterAnnotation(NonRequiredParam.class))
//            return new UserId(null);

        Claims claims = JwtTokenUtils.ParseJwtToken(token, jwtProperties.getSecretKey());
        return new UserId(claims.get("user_id", String.class));
    }
}
