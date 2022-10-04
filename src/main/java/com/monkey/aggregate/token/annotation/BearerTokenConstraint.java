package com.monkey.aggregate.token.annotation;

import com.monkey.aggregate.token.validation.BearerTokenValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = BearerTokenValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface BearerTokenConstraint {
    String message() default "invalid token";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
