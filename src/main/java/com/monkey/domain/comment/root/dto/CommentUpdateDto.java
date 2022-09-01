package com.monkey.domain.comment.root.dto;

import com.monkey.view.UserSession;
public class CommentUpdateDto extends UserSession {
    private Long commentId;
    private String content;
    private boolean isSecret;

    public CommentUpdateDto(Long commentId, String content, boolean isSecret) {
        this.commentId = commentId;
        this.content = content;
        this.isSecret = isSecret;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
}
