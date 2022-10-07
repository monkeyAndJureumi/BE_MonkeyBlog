package com.monkey.aggregate.user.enums;

import com.monkey.enums.EntityEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum UserSkill implements EntityEnum {
    JAVA, JAVASCRIPT, JPA, PYTHON, SPRING, SPRING_BOOT, VUE, C, C_Plus;


    public static UserSkill create(String value) {
        for(UserSkill userSkill : UserSkill.values()) {
            if (userSkill.name().equalsIgnoreCase(value))
                return userSkill;
        }

        log.error("[{}]값과 일치하는 Enum이 존재하지 않습니다.", value);
        return null;
    }

    @Override
    public String getName() {
        return name();
    }
}
