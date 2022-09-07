package com.monkey.aggregate.post.dto;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSaveDto extends UserSessionDto {
    private String content;

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
