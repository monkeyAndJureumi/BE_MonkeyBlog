package com.monkey.validation;

import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Validator;
import java.lang.annotation.Annotation;

@RequiredArgsConstructor
public class AbstractRequestValidator<A extends Annotation, V, G extends Class<?>> implements ConstraintValidator<A, V> {
    private final Validator validator;
    private final G[] groups;

    @Override
    public void initialize(A constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(V value, ConstraintValidatorContext context) {
        validator.validate(value, groups);
        return false;
    }
}
