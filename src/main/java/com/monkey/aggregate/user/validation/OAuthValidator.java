package com.monkey.aggregate.user.validation;

import com.monkey.aggregate.user.annotation.OAuthValid;
import com.monkey.aggregate.user.dto.token.AccessRequestDto;
import com.monkey.aggregate.user.marker.Authorization;
import com.monkey.aggregate.user.marker.Refresh;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;


@Deprecated
@RequiredArgsConstructor
public class OAuthValidator implements ConstraintValidator<OAuthValid, AccessRequestDto> {
    private final Validator validator;

    @Override
    public boolean isValid(AccessRequestDto value, ConstraintValidatorContext context) {
        Set<ConstraintViolation<Object>> constraintViolations;

        switch (value.getGrantType()) {
            case ACCESS_TOKEN:
                constraintViolations = validator.validate(value, Authorization.class);
                if (!CollectionUtils.isEmpty(constraintViolations)) {
                    context.disableDefaultConstraintViolation();
                    constraintViolations.forEach(constraintViolation -> context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                            .addPropertyNode(constraintViolation.getPropertyPath().toString())
                            .addConstraintViolation());
                    return false;
                }
                break;

            case REFRESH_TOKEN:
                constraintViolations = validator.validate(value, Refresh.class);
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
