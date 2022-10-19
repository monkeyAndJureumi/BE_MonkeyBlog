package com.monkey.context.member.domain;

import com.monkey.context.member.enums.OauthType;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberSocialConverter extends AbstractEnumConverter<OauthType> {
    public MemberSocialConverter() {
        super(OauthType.class, false, false);
    }
}
