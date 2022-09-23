package com.monkey.aggregate.user.validation;

import com.monkey.aggregate.user.annotation.JwtTokenValid;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JwtTokenValidation implements ConstraintValidator<JwtTokenValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value) || !value.startsWith("Bearer "))
            return false;
        return true;
    }
}
