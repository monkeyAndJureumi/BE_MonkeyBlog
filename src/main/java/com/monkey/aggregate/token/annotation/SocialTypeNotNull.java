package com.monkey.aggregate.token.annotation;

import com.monkey.aggregate.token.validation.SocialTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = SocialTypeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface SocialTypeNotNull {
    String message() default "invalid social_type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
