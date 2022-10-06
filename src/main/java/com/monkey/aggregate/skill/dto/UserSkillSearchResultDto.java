package com.monkey.aggregate.skill.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.skill.enums.Skill;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSkillSearchResultDto {
    @JsonProperty("result")
    private List<Skill> result;

    public UserSkillSearchResultDto(List<Skill> result) {
        this.result = result;
    }
}
