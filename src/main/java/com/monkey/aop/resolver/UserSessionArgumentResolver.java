package com.monkey.aop.resolver;

import com.monkey.aggregate.user.entity.UserId;
import com.monkey.utils.TokenUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;


@Component
public class UserSessionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) webRequest.getNativeRequest();
        String token = httpRequest.getHeader("Authorization");

        Long userId = TokenUtils.ParseJwtToken(token);
        return new UserId(userId);
    }
}
