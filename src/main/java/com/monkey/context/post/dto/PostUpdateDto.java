package com.monkey.context.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.post.enums.PostStatus;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostUpdateDto extends UserSessionDto {
    @JsonProperty(value = "post_id")
    private Long postId;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "is_secret")
    private boolean isSecret;

    @JsonProperty(value = "status")
    private PostStatus status;

    public Long getPostId() {
        return postId;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecret() {
        return isSecret;
    }
    public PostStatus getStatus() {
        return status;
    }
}
