package com.monkey.domain.temporary.view;

import com.monkey.domain.temporary.entity.TemporaryPostId;
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
