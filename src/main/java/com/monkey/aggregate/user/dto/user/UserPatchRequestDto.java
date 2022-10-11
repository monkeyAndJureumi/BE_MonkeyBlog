package com.monkey.aggregate.user.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.annotation.UserPatchRequestConstraint;
import com.monkey.aggregate.user.enums.UserGrantType;
import com.monkey.aggregate.user.validation.marker.UserProfileUpdate;
import com.monkey.dto.UserSessionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@UserPatchRequestConstraint
public class UserPatchRequestDto extends UserSessionDto {
    @JsonProperty("grant_type")
    @Schema(description = "요청타입")
    private UserGrantType grantType;

    @JsonProperty("user_profile")
    @NotNull(groups = UserProfileUpdate.class, message = "user_profile is null")
    private UserProfileUpdateDto userProfileUpdateDto;
}
