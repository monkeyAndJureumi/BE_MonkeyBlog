package com.monkey.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.context.member.domain.MemberId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserSessionDto {
    @JsonIgnore
    private MemberId memberId;

    @Schema(hidden = true)
    public void setSession(String id) {
        this.memberId = new MemberId(id);
    }
}
