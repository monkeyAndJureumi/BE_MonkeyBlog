package com.monkey.aggregate.user.validation;

import com.monkey.aggregate.user.annotation.GrantTypeNotNull;
import com.monkey.aggregate.user.enums.GrantType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GrantTypeValidator implements ConstraintValidator<GrantTypeNotNull, GrantType> {
    @Override
    public boolean isValid(GrantType value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return true;
    }
}
