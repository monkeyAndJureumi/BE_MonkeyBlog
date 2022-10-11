package com.monkey.aggregate.user.enums;

import com.monkey.enums.EntityEnum;
import lombok.Getter;

@Getter
public enum UserStatus implements EntityEnum {
    ACTIVATE, DEACTIVATE;

    @Override
    public String getName() {
        return name();
    }
}
