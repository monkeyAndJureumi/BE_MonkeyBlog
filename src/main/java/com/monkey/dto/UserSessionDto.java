package com.monkey.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.domain.UserId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class UserSessionDto {
    @JsonIgnore
    private UserId userId;

    @Schema(hidden = true)
    public void setSession(String id) {
        this.userId = new UserId(id);
    }
}
