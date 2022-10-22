package com.monkey.context.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.context.member.dto.member.MemberPatchRequestDto;
import com.monkey.context.member.service.MemberService;
import lombok.Getter;

import java.util.function.BiConsumer;


@Getter
public enum UserGrantType {
    UPDATE((memberService, dto) -> memberService.updateProfile(dto.getMemberId(), dto.getMemberProfileUpdateDto())),
    DEACTIVATE((memberService, dto) -> memberService.deactivateUser(dto.getMemberId()));

    private final BiConsumer<MemberService, MemberPatchRequestDto> consumer;

    UserGrantType(BiConsumer<MemberService, MemberPatchRequestDto> consumer) {
        this.consumer = consumer;
    }

    @JsonCreator
    public static UserGrantType create(String value) {
        for (UserGrantType grantType : UserGrantType.values()) {
            if (grantType.name().toLowerCase().equals(value))
                return grantType;
        }

        throw new IllegalArgumentException("invalid grant_type");
    }
}
