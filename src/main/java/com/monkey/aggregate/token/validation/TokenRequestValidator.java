package com.monkey.aggregate.token.validation;

import com.monkey.aggregate.token.annotation.TokenRequestConstraint;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.validation.sequence.AccessTypeSequence;
import com.monkey.aggregate.token.validation.sequence.RefreshTypeSequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
public class TokenRequestValidator implements ConstraintValidator<TokenRequestConstraint, TokenRequestDto> {
    private final Validator validator;

    @Override
    public boolean isValid(TokenRequestDto value, ConstraintValidatorContext context) {
        if (value.getGrantType() == null)
            return false;

        Set<ConstraintViolation<Object>> constraintViolations = null;
        switch (value.getGrantType()) {
            case ACCESS_TOKEN:
                constraintViolations = validator.validate(value, AccessTypeSequence.class);
                break;
            case REFRESH_TOKEN:
                constraintViolations = validator.validate(value, RefreshTypeSequence.class);
                break;
        }

        if (!CollectionUtils.isEmpty(constraintViolations)) {
            context.disableDefaultConstraintViolation();
            constraintViolations.forEach(constraintViolation -> context.buildConstraintViolationWithTemplate(constraintViolation.getMessageTemplate())
                    .addPropertyNode(constraintViolation.getPropertyPath().toString())
                    .addConstraintViolation());
            return false;
        }

        return true;
    }
}
