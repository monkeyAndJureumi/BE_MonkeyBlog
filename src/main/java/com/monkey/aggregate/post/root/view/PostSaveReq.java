package com.monkey.aggregate.post.root.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveReq extends UserSession {
    @JsonProperty("content")
    private String content;

    public PostSaveReq(String content) {
        this.content = content;
    }
}
