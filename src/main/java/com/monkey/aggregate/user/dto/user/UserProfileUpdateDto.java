package com.monkey.aggregate.user.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.skill.enums.Skill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfileUpdateDto {
    @JsonProperty("git_url")
    private String gitUrl;

    @JsonProperty("skill_list")
    private Set<Skill> skillList = new LinkedHashSet<>();
}
