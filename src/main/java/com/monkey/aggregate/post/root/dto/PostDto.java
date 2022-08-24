package com.monkey.aggregate.post.root.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostDto {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostDto(String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
