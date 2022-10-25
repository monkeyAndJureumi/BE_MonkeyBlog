package com.monkey.context.member.enums;

import com.monkey.enums.EntityEnumerable;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum MemberSkill implements EntityEnumerable {
    JAVA("Java"), JAVASCRIPT("JavaScript"), JPA("Jpa"), PYTHON("Python"), SPRING("Spring"), SPRING_BOOT("Spring Boot"), VUE("Vue"), C("C"), C_Plus("C++");

    private final String value;

    MemberSkill(String value) {
        this.value = value;
    }

    public static MemberSkill create(String value) {
        for(MemberSkill memberSkill : MemberSkill.values()) {
            if (memberSkill.getValue().equalsIgnoreCase(value))
                return memberSkill;
        }

        throw new IllegalArgumentException("입력하신 값과 일치하는 Enum이 존재하지 않습니다.");
    }

    @Override
    public String getName() {
        return name();
    }
}
