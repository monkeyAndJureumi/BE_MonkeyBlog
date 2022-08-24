package com.monkey.domain.user.entity;

import com.monkey.domain.user.enums.UserSocial;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<UserSocial> {
    public UserSocialConverter() {
        super(UserSocial.class, false, false);
    }
}
