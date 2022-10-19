package com.monkey.context.member.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "user_profile", description = "grant_type이 UPDATE인 경우에 입력")
public class UserProfileUpdateDto {
    @JsonProperty("nick_name")
    @Schema(description = "닉네임")
    private String nickName;

    @JsonProperty("git_url")
    @Schema(description = "깃 주소")
    private String gitUrl;

    @JsonProperty("skill_list")
    @Schema(description = "유저 기술스택 목록")
    private Set<String> userSkillList = new LinkedHashSet<>();
}
