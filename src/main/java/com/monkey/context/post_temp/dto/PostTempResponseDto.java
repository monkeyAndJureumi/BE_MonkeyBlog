package com.monkey.context.post_temp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.post_temp.domain.PostTemp;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostTempResponseDto {
    @JsonProperty("content")
    private String content;

    public PostTempResponseDto(PostTemp post) {
        this.content = post.getContent();
    }
}
