package com.monkey.context.member.domain;

import com.monkey.context.member.enums.MemberStatus;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberStatusConverter extends AbstractEnumConverter<MemberStatus> {
    public MemberStatusConverter() {
        super(MemberStatus.class, false, false);
    }
}
