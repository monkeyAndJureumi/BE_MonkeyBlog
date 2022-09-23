package com.monkey.aggregate.user.annotation;

import com.monkey.aggregate.user.validation.OAuthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Deprecated
@Constraint(validatedBy = OAuthValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface OAuthValid {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
