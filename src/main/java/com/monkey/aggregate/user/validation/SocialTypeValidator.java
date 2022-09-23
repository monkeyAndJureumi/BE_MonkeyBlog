package com.monkey.aggregate.user.validation;

import com.monkey.aggregate.user.annotation.SocialTypeNotNull;
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
