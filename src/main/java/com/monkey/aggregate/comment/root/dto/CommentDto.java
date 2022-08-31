package com.monkey.aggregate.comment.root.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.root.entity.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
    @JsonIgnore
    private Long refUserId;
    private Author author;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private boolean hasReply;
    private boolean isSecrete;

    public CommentDto(Long refUserId, Long authorId, String authorName, Long commentId, String content, LocalDateTime createdAt, boolean hasReply, boolean isSecrete) {
        this.refUserId = refUserId;
        this.author = new Author(authorId, authorName);
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.hasReply = hasReply;
        this.isSecrete = isSecrete;
    }

    public void setSecreteContent(UserId loginUserId) {
        if (this.isSecrete && !this.author.getId().equals(loginUserId.getId()) && !refUserId.equals(loginUserId.getId())) {
            this.content = "비밀 댓글입니다.";
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Author {
        private Long id;
        private String name;

        public Author(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
