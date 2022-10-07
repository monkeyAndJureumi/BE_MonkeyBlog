package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.UserSkill;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserSkillConverter extends AbstractEnumConverter<UserSkill> {
    public UserSkillConverter() {
        super(UserSkill.class, false, false);
    }
}
