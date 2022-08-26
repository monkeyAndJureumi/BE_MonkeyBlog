package com.monkey.aggregate.comment.root.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.aggregate.user.root.entity.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    @JsonIgnore
    private Long refUserId;
    private Long authorId;
    private String authorName;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private boolean hasReply;
    private boolean isSecrete;

    public CommentResponseDto(Long refUserId, Long authorId, String authorName, Long commentId, String content, LocalDateTime createdAt, boolean hasReply, boolean isSecrete) {
        this.refUserId = refUserId;
        this.authorId = authorId;
        this.authorName = authorName;
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.hasReply = hasReply;
        this.isSecrete = isSecrete;
    }

    public void setSecreteContent(UserId loginUserId) {
        if (this.isSecrete && !this.authorId.equals(loginUserId.getId()) && !refUserId.equals(loginUserId.getId())) {
            this.content = "비밀 댓글입니다.";
        }
    }}
