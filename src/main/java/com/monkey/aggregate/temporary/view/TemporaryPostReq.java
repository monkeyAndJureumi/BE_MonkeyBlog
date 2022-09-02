package com.monkey.aggregate.temporary.view;

import com.monkey.aggregate.temporary.domain.TemporaryPostId;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemporaryPostReq extends UserSessionDto {
    private String postId;

    public TemporaryPostId getPostId() {
        return new TemporaryPostId(postId);
    }
}
