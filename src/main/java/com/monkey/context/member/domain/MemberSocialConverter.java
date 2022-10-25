package com.monkey.context.member.domain;

import com.monkey.context.member.enums.OAuthType;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Deprecated
@Converter(autoApply = true)
public class MemberSocialConverter extends AbstractEnumConverter<OAuthType> {
    public MemberSocialConverter() {
        super(OAuthType.class, false, false);
    }
}
