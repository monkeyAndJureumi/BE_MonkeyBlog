package com.monkey.aggregate.comment.root.view;

import com.monkey.view.UserSession;
import lombok.Getter;

@Getter
public class CommentSaveReq extends UserSession {
    private Long postId;
    private Long rootCommentId;
    private String content;

    public CommentSaveReq(Long postId, Long rootCommentId, String content) {
        this.postId = postId;
        this.rootCommentId = rootCommentId;
        this.content = content;
    }
}
