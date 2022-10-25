package com.monkey.context.member.domain;

import com.monkey.context.member.enums.MemberSkill;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberSkillConverter extends AbstractEnumConverter<MemberSkill> {
    public MemberSkillConverter() {
        super(MemberSkill.class, false, false);
    }
}
