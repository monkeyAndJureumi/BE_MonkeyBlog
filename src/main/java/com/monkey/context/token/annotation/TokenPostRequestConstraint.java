package com.monkey.context.token.annotation;

import com.monkey.context.token.validator.TokenRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = TokenRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface TokenPostRequestConstraint {
    String message() default "grant_type is null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
