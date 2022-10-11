package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.OauthType;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<OauthType> {
    public UserSocialConverter() {
        super(OauthType.class, false, false);
    }
}
