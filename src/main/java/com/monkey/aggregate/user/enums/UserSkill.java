package com.monkey.aggregate.user.enums;

import com.monkey.enums.EntityEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum UserSkill implements EntityEnum {
    JAVA("Java"), JAVASCRIPT("JavaScript"), JPA("Jpa"), PYTHON("Python"), SPRING("Spring"), SPRING_BOOT("Spring Boot"), VUE("Vue"), C("C"), C_Plus("C++");

    private final String value;

    UserSkill(String value) {
        this.value = value;
    }

    public static UserSkill create(String value) {
        for(UserSkill userSkill : UserSkill.values()) {
            if (userSkill.getValue().equalsIgnoreCase(value))
                return userSkill;
        }

        throw new IllegalArgumentException("입력하신 값과 일치하는 Enum이 존재하지 않습니다.");
    }

    @Override
    public String getName() {
        return name();
    }
}
