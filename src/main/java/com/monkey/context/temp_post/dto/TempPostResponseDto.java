package com.monkey.context.temp_post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.temp_post.domain.TempPost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPostResponseDto {
    @JsonProperty("temp_post_id")
    private String tempPostId;
    @JsonProperty("content")
    private String content;

    public TempPostResponseDto(TempPost post) {
        this.tempPostId = post.getTempPostId().getId();
        this.content = post.getContent();
    }
}
