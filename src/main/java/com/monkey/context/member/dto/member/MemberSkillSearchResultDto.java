package com.monkey.context.member.dto.member;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberSkillSearchResultDto {
    @JsonProperty("result")
    private List<String> result;

    public MemberSkillSearchResultDto(List<String> result) {
        this.result = result;
    }
}
