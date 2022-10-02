package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.TokenRequestConstraint;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.marker.AccessType;
import com.monkey.aggregate.token.marker.RefreshType;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;


@RequiredArgsConstructor
public class TokenRequestValidator implements ConstraintValidator<TokenRequestConstraint, TokenRequestDto> {
    private final Validator validator;

    @Override
    public boolean isValid(TokenRequestDto value, ConstraintValidatorContext context) {
        Set<ConstraintViolation<Object>> constraintViolations;

        switch (value.getGrant_type()) {
            case ACCESS_TOKEN:
                constraintViolations = validator.validate(value, AccessType.class);
                if (!CollectionUtils.isEmpty(constraintViolations)) {
                    context.disableDefaultConstraintViolation();
                    constraintViolations.forEach(constraintViolation -> context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                            .addPropertyNode(constraintViolation.getPropertyPath().toString())
                            .addConstraintViolation());
                    return false;
                }
                break;

            case REFRESH_TOKEN:
                constraintViolations = validator.validate(value, RefreshType.class);
                if (!CollectionUtils.isEmpty(constraintViolations)) {
                    context.disableDefaultConstraintViolation();
                    constraintViolations.forEach(constraintViolation ->
                            context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                                    .addPropertyNode(constraintViolation.getPropertyPath().toString())
                                    .addConstraintViolation());
                    return false;
                }
                break;
        }

        return true;
    }
}
