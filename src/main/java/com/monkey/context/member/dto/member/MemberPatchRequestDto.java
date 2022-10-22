package com.monkey.context.member.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.member.annotation.MemberPatchRequestConstraint;
import com.monkey.context.member.enums.UserGrantType;
import com.monkey.context.member.validation.marker.MemberProfileUpdate;
import com.monkey.dto.UserSessionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@MemberPatchRequestConstraint
public class MemberPatchRequestDto extends UserSessionDto {
    @JsonProperty("grant_type")
    @Schema(description = "요청타입(소문자로 입력)")
    private UserGrantType grantType;

    @JsonProperty("user_profile")
    @NotNull(groups = MemberProfileUpdate.class, message = "user_profile is null")
    private MemberProfileUpdateDto memberProfileUpdateDto;
}
