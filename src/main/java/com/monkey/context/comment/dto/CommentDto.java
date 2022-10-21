package com.monkey.context.comment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentDto {
    @JsonIgnore
    private RefAuthor refUserId;
    private Author author;
    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private boolean hasReply;
    private boolean isSecrete;

    public CommentDto(String refUserId, String authorId, String authorName, Long commentId, String content, LocalDateTime createdAt, boolean hasReply, boolean isSecrete) {
        this.refUserId = new RefAuthor(refUserId);
        this.author = new Author(authorId, authorName);
        this.commentId = commentId;
        this.content = content;
        this.createdAt = createdAt;
        this.hasReply = hasReply;
        this.isSecrete = isSecrete;
    }

    public void setSecreteComment() {
        this.content = "비밀 댓글입니다.";
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RefAuthor {
        private String id;

        public RefAuthor(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o instanceof MemberId) {
                MemberId refAuthor = (MemberId) o;
                return Objects.equals(id, refAuthor.getId());
            }
            return false;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Author {
        private String id;
        private String name;

        public Author(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (o.getClass().equals(MemberId.class)) {
                MemberId author = (MemberId) o;
                return Objects.equals(id, author.getId());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }
}
