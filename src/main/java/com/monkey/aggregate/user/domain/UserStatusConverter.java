package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.UserStatus;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter extends AbstractEnumConverter<UserStatus> {
    public UserStatusConverter() {
        super(UserStatus.class, false, false);
    }
}
