package com.monkey.aggregate.temporary.view;

import com.monkey.aggregate.temporary.entity.TemporaryPostId;
import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemporaryPostReq extends UserSession {
    private String postId;

    public TemporaryPostId getPostId() {
        return new TemporaryPostId(postId);
    }
}
