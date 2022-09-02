package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.UserSocial;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<UserSocial> {
    public UserSocialConverter() {
        super(UserSocial.class, false, false);
    }
}
