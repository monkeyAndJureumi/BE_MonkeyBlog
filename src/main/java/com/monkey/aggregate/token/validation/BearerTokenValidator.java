package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.BearerTokenConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BearerTokenValidator implements ConstraintValidator<BearerTokenConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && value.startsWith("Bearer ");
    }
}
