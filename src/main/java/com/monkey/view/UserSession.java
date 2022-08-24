package com.monkey.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.entity.UserId;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class UserSession {
    @JsonIgnore
    private UserId userId;

    public void setUserId(Long userId) {
        this.userId = new UserId(userId);
    }

    public UserId getUserId() {
        return userId;
    }
}
