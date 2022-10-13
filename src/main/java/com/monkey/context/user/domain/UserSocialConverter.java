package com.monkey.context.user.domain;

import com.monkey.context.user.enums.OauthType;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSocialConverter extends AbstractEnumConverter<OauthType> {
    public UserSocialConverter() {
        super(OauthType.class, false, false);
    }
}
