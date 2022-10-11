package com.monkey.aggregate.token.validator;

import com.monkey.aggregate.token.annotation.JwtTokenConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JwtTokenValidator implements ConstraintValidator<JwtTokenConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.startsWith("Bearer ");
    }
}
