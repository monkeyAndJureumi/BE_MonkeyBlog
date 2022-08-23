package com.monkey.aggregate.comment.dto;

import com.monkey.aggregate.comment.domain.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
    private String content;
    private LocalDateTime createdAt;
    private boolean hasReply;

    public CommentDto(final String content, LocalDateTime createdAt, boolean hasReply) {
        this.content = content;
        this.createdAt = createdAt;
        this.hasReply = hasReply;
    }
}
