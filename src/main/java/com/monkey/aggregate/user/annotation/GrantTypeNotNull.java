package com.monkey.aggregate.user.annotation;


import com.monkey.aggregate.user.validation.GrantTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = GrantTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface GrantTypeNotNull {
    String message() default "invalid grant_type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
