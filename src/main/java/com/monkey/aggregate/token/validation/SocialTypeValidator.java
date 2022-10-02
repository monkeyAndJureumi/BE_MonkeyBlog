package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.SocialTypeNotNull;
import com.monkey.aggregate.user.enums.SocialType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SocialTypeValidator implements ConstraintValidator<SocialTypeNotNull, SocialType> {
    @Override
    public boolean isValid(SocialType value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return true;
    }
}
