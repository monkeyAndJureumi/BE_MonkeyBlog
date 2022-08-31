package com.monkey.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.root.entity.UserId;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class UserSession {
    @JsonIgnore
    private UserId userId;

    @JsonIgnore
    private String uuid;

    public void setUserId(Long userId) {
        this.userId = new UserId(userId);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getUuid() {
        return uuid;
    }

    public UserSession(UserId userId, String uuid) {
        this.userId = userId;
        this.uuid = uuid;
    }
}
