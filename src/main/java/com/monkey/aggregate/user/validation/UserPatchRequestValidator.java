package com.monkey.aggregate.user.validation;

import com.monkey.aggregate.user.annotation.UserPatchRequestConstraint;
import com.monkey.aggregate.user.dto.user.UserPatchRequestDto;
import com.monkey.aggregate.user.enums.UserGrantType;
import com.monkey.aggregate.user.validation.marker.UserProfileUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
public class UserPatchRequestValidator implements ConstraintValidator<UserPatchRequestConstraint, UserPatchRequestDto> {
    private final Validator validator;

    @Override
    public void initialize(UserPatchRequestConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserPatchRequestDto value, ConstraintValidatorContext context) {
        if (value.getGrantType() == null)
            return false;

        Set<ConstraintViolation<Object>> constraintViolations = validate(value);

        if (!CollectionUtils.isEmpty(constraintViolations)) {
            context.disableDefaultConstraintViolation();
            constraintViolations.forEach(constraintViolation -> context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                    .addPropertyNode(constraintViolation.getPropertyPath().toString())
                    .addConstraintViolation());
            return false;
        }

        return true;
    }

    private Set<ConstraintViolation<Object>> validate(UserPatchRequestDto value) {
        if (value.getGrantType() == UserGrantType.UPDATE) {
            return validator.validate(value.getUserProfileUpdateDto(), UserProfileUpdate.class);
        }

        return null;
    }
}
