package com.monkey.aggregate.skill.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Skill {
    JAVA, JAVASCRIPT, JPA, PYTHON, SPRING, SPRING_BOOT;

    public static Skill create(String value) {
        for(Skill skill : Skill.values()) {
            if (skill.name().equals(value))
                return skill;
        }

        log.error("[{}]값과 일치하는 Enum이 존재하지 않습니다.", value);
        return null;
    }
}
