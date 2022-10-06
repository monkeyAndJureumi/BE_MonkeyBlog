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

    @JsonIgnore
    private String userCode;

    public void setSession(String id) {
        this.userId = new UserId(id);
    }
    public void setUserCode(String code) {
        this.userCode = code;
    }
}
