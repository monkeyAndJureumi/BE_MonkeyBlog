package com.monkey.context.member.domain;

import com.monkey.context.member.enums.UserSkill;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class MemberSkillConverter extends AbstractEnumConverter<UserSkill> {
    public MemberSkillConverter() {
        super(UserSkill.class, false, false);
    }
}
