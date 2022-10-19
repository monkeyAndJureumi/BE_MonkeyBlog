package com.monkey.context.post.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostResponseDto {
    private Author author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(String userId, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.author = new Author(userId, name);
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Author {
        private String userId;
        private String name;

        private Author(String userId, String name) {
            this.userId = userId;
            this.name = name;
        }
    }
}
