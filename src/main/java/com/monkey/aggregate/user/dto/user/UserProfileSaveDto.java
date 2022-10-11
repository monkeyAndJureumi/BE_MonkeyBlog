package com.monkey.aggregate.user.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.enums.UserSkill;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileSaveDto {
    @JsonProperty("skill_list")
    @Schema(description = "유저 기술스택 목록")
    private Set<String> userSkillList = new LinkedHashSet<>();
}
