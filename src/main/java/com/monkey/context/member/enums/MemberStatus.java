package com.monkey.context.member.enums;

import com.monkey.enums.EntityEnum;
import lombok.Getter;

@Getter
public enum MemberStatus implements EntityEnum {
    ACTIVATE, DEACTIVATE;

    @Override
    public String getName() {
        return name();
    }
}
