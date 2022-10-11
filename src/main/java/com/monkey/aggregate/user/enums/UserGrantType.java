package com.monkey.aggregate.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.aggregate.user.dto.user.UserPatchRequestDto;
import com.monkey.aggregate.user.service.UserService;
import lombok.Getter;

import java.util.function.BiConsumer;


@Getter
public enum UserGrantType {
    UPDATE((userService, dto) -> userService.updateProfile(dto.getUserId(), dto.getUserProfileUpdateDto())),
    DEACTIVATE((userService, dto) -> userService.deactivateUser(dto.getUserId()));

    private final BiConsumer<UserService, UserPatchRequestDto> consumer;

    UserGrantType(BiConsumer<UserService, UserPatchRequestDto> consumer) {
        this.consumer = consumer;
    }

    @JsonCreator
    public static UserGrantType create(String value) {
        for (UserGrantType grantType : UserGrantType.values()) {
            if (grantType.name().equals(value))
                return grantType;
        }

        return null;
    }
}
