package com.monkey.context.comment.domain;

import com.monkey.context.comment.dto.CommentUpdateDto;
import com.monkey.context.post.domain.PostId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comment", referencedColumnName = "id")
    private Comment refComment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "refComment")
    private Set<Comment> replyComments = new LinkedHashSet<>();

    @Column(name = "content", nullable = false)
    private String content;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "post_id"))
    private PostId postId;

    @Embedded
    private CommentAuthor author;

    @Column(name = "hasReply")
    private boolean hasReply;

    @Column(name = "isSecrete")
    private boolean isSecrete;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public void update(CommentUpdateDto dto) {
        this.content = dto.getContent();
        this.isSecrete = dto.getIsSecret();
        this.modifiedAt = LocalDateTime.now();
    }

    public void delete() {
        this.content = "삭제된 댓글입니다.";
        this.author = null;
        this.modifiedAt = LocalDateTime.now();
    }

    @Builder
    public Comment(Comment refComment, PostId postId, CommentAuthor author, String content, boolean isSecrete) {
        this.refComment = refComment;
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.isSecrete = isSecrete;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void setHasReplyTrue() {
        this.hasReply = true;
    }
}
