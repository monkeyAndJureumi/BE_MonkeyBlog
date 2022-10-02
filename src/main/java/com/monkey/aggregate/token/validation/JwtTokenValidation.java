package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.JwtTokenConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JwtTokenValidation implements ConstraintValidator<JwtTokenConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(value) || !value.startsWith("Bearer "))
            return false;
        return true;
    }
}
