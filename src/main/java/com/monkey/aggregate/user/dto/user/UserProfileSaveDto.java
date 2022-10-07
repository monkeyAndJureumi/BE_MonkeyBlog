package com.monkey.aggregate.user.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.enums.UserSkill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileSaveDto {
    @JsonProperty("skill_list")
    private Set<UserSkill> userSkillList = new LinkedHashSet<>();
}
