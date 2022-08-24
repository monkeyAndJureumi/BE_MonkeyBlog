package com.monkey.aggregate.comment.view;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CommentDeleteReq extends UserSession {
    private Long commentId;
}