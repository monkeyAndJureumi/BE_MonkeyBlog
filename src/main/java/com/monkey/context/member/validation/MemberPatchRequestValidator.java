package com.monkey.context.member.validation;

import com.monkey.context.member.annotation.MemberPatchRequestConstraint;
import com.monkey.context.member.dto.member.MemberPatchRequestDto;
import com.monkey.context.member.enums.UserGrantType;
import com.monkey.context.member.validation.marker.MemberProfileUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
public class MemberPatchRequestValidator implements ConstraintValidator<MemberPatchRequestConstraint, MemberPatchRequestDto> {
    private final Validator validator;

    @Override
    public void initialize(MemberPatchRequestConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberPatchRequestDto value, ConstraintValidatorContext context) {
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

    private Set<ConstraintViolation<Object>> validate(MemberPatchRequestDto value) {
        if (value.getGrantType() == UserGrantType.UPDATE) {
            return validator.validate(value.getMemberProfileUpdateDto(), MemberProfileUpdate.class);
        }

        return null;
    }
}
