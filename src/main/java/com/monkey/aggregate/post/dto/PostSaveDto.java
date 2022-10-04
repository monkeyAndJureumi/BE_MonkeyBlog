package com.monkey.aggregate.post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveDto extends UserSessionDto {
    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "is_secret")
    private boolean isSecrete;

    public PostSaveDto(String content, boolean isSecrete) {
        this.content = content;
        this.isSecrete = isSecrete;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsSecrete() {
        return isSecrete;
    }

}
