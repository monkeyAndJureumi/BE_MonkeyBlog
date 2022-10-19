package com.monkey.context.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.context.member.dto.user.UserPatchRequestDto;
import com.monkey.context.member.service.MemberService;
import lombok.Getter;

import java.util.function.BiConsumer;


@Getter
public enum UserGrantType {
    update((memberService, dto) -> memberService.updateProfile(dto.getMemberId(), dto.getUserProfileUpdateDto())),
    deactivate((memberService, dto) -> memberService.deactivateUser(dto.getMemberId()));

    private final BiConsumer<MemberService, UserPatchRequestDto> consumer;

    UserGrantType(BiConsumer<MemberService, UserPatchRequestDto> consumer) {
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
