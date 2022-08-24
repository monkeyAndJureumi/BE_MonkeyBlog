package com.monkey.aggregate.user.root.entity;

import com.monkey.aggregate.user.root.enums.UserSocial;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<UserSocial> {
    public UserSocialConverter() {
        super(UserSocial.class, false, false);
    }
}
