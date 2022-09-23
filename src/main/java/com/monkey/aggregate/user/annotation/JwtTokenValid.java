package com.monkey.aggregate.user.annotation;

import com.monkey.aggregate.user.validation.JwtTokenValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = JwtTokenValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface JwtTokenValid {
    String message() default "invalid token";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
