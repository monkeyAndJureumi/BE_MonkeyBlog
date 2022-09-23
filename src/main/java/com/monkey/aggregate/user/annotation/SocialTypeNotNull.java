package com.monkey.aggregate.user.annotation;

import com.monkey.aggregate.user.validation.SocialTypeValidator;

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
