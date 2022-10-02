package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.GrantTypeConstraint;
import com.monkey.aggregate.token.enums.GrantType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GrantTypeValidator implements ConstraintValidator<GrantTypeConstraint, GrantType> {
    private Class<?>[] groups;

    @Override
    public void initialize(GrantTypeConstraint constraintAnnotation) {
        this.groups = constraintAnnotation.groups();
    }

    @Override
    public boolean isValid(GrantType value, ConstraintValidatorContext context) {

        switch (value) {
            case ACCESS_TOKEN:


        }
        return true;
    }
}
