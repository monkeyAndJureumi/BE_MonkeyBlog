package com.monkey.context.post.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.enums.EntityEnum;
import lombok.Getter;

@Getter
public enum PostStatus implements EntityEnum {
    ACTIVATE, DEACTIVATE, TEMPORARY;

    @Override
    public String getName() {
        return name();
    }

    @JsonCreator
    public static PostStatus create(String value) {
        for (PostStatus status : PostStatus.values()) {
            if (status.getName().toLowerCase().equals(value))
                return status;
        }

        return null;
    }
}
