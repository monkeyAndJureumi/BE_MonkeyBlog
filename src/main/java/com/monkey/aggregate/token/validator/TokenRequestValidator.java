package com.monkey.aggregate.token.validator;

import com.monkey.aggregate.token.annotation.TokenPostRequestConstraint;
import com.monkey.aggregate.token.dto.TokenPostRequestDto;
import com.monkey.aggregate.token.validator.sequence.AccessRequestSequence;
import com.monkey.aggregate.token.validator.sequence.RefreshRequestSequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;


@Slf4j
@RequiredArgsConstructor
public class TokenRequestValidator implements ConstraintValidator<TokenPostRequestConstraint, TokenPostRequestDto> {
    private final Validator validator;

    @Override
    public boolean isValid(TokenPostRequestDto value, ConstraintValidatorContext context) {
        if (value.getGrantType() == null)
            return false;

        Set<ConstraintViolation<Object>> constraintViolations = null;
        switch (value.getGrantType()) {
            case ACCESS_TOKEN:
                constraintViolations = validator.validate(value, AccessRequestSequence.class);
                break;
            case REFRESH_TOKEN:
                constraintViolations = validator.validate(value, RefreshRequestSequence.class);
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
