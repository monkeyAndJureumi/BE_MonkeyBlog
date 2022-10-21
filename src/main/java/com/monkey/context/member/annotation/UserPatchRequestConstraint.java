package com.monkey.context.member.annotation;

import com.monkey.context.member.validation.UserPatchRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UserPatchRequestValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface UserPatchRequestConstraint {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
