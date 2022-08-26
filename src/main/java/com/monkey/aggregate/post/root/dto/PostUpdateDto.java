package com.monkey.aggregate.post.root.dto;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostUpdateDto extends UserSession {
    private Long postId;
    private String content;
    private boolean isSecret;

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
}
