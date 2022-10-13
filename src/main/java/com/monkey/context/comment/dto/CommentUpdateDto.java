package com.monkey.context.comment.dto;

import com.monkey.dto.UserSessionDto;
public class CommentUpdateDto extends UserSessionDto {
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
