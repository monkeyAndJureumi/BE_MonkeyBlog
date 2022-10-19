package com.monkey.context.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.context.member.dto.user.UserPatchRequestDto;
import com.monkey.context.member.service.UserService;
import lombok.Getter;

import java.util.function.BiConsumer;


@Getter
public enum UserGrantType {
    update((userService, dto) -> userService.updateProfile(dto.getMemberId(), dto.getUserProfileUpdateDto())),
    deactivate((userService, dto) -> userService.deactivateUser(dto.getMemberId()));

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

        throw new IllegalArgumentException("invalid grant_type");
    }
}
