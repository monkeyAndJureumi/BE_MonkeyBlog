package com.monkey.aggregate.skill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.skill.enums.Skill;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSkillSearchResultDto {
    @JsonProperty("skill_list")
    private List<Skill> skillList;

    public UserSkillSearchResultDto(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
