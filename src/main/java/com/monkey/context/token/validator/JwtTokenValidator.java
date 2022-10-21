package com.monkey.context.token.validator;

import com.monkey.context.token.annotation.JwtTokenConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JwtTokenValidator implements ConstraintValidator<JwtTokenConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.startsWith("Bearer ");
    }
}
