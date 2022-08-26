package com.monkey.aggregate.post.root.dto;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveDto extends UserSession {
    private String content;

    private boolean isSecrete;

    public PostSaveDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecrete() {
        return isSecrete;
    }
}
