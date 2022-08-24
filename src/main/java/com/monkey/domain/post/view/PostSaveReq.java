package com.monkey.domain.post.view;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveReq extends UserSession {
    private String content;

    public PostSaveReq(String content) {
        this.content = content;
    }
}
