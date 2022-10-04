package com.monkey.aggregate.token.annotation;

import com.monkey.aggregate.token.validation.TokenRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = TokenRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TokenRequestConstraint {
    String message() default "grant_type is null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
