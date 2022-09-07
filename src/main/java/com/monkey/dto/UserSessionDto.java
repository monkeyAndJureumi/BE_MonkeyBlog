package com.monkey.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.domain.UserId;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public abstract class UserSessionDto {
    @JsonIgnore
    private UserId userId;

    public void setSession(Long id) {
        this.userId = new UserId(id);
    }
}
