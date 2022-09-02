package com.monkey.aggregate.post.dto;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostUpdateDto extends UserSessionDto {
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
