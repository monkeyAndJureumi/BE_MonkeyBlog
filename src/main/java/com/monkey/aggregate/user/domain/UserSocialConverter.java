package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<SocialType> {
    public UserSocialConverter() {
        super(SocialType.class, false, false);
    }
}
