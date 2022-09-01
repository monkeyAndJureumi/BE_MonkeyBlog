package com.monkey.domain.comment.root.dto;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommentSaveDto extends UserSession {
    private Long postId;

    private Long refCommentId;

    private String content;

    private boolean isSecret;

    public Long getPostId() {
        return postId;
    }

    public Long getRefCommentId() {
        return refCommentId;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
}
