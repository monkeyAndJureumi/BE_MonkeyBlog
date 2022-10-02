package com.monkey.aggregate.token.annotation;


import com.monkey.aggregate.token.validation.GrantTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = GrantTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface GrantTypeConstraint {
    String message() default "invalid grant_type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
