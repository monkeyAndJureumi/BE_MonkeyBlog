package com.monkey.domain.comment.view;

import com.monkey.view.UserSession;
import lombok.Getter;

@Getter
public class CommentUpdateReq extends UserSession {
    private Long commentId;
    private String content;

    public CommentUpdateReq(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}
