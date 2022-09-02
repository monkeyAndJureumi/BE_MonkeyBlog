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
    private String userName;

    @JsonIgnore
    private String uuid;

    public void setSession(Long id, String name, String uuid) {
        this.userId = new UserId(id);
        this.userName = name;
        this.uuid = uuid;
    }
}
