package com.monkey.context.member.enums;

import com.monkey.enums.EntityEnumerable;
import lombok.Getter;

@Getter
public enum MemberStatus implements EntityEnumerable {
    ACTIVATE, DEACTIVATE;

    @Override
    public String getName() {
        return name();
    }
}
