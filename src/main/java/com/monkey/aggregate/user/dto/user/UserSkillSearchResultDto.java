package com.monkey.aggregate.user.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class UserSkillSearchResultDto {
    @JsonProperty("result")
    private List<String> result;

    public UserSkillSearchResultDto(List<String> result) {
        this.result = result;
    }
}
