package com.monkey.aggregate.comment.domain;

import com.monkey.aggregate.comment.dto.CommentUpdateDto;
import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import lombok.AccessLevel;
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
public class Comment implements PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ref_comment", referencedColumnName = "id")
    private Comment refComment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "refComment")
    private Set<Comment> replyComments = new LinkedHashSet<>();

    @Column(name = "content")
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

    /**
     * * Comment 객체를 저장할 때 refComment 필드가 null 이 아니라면,
     * 하위댓글로 판단하여 refComment 객체의 reply 필드를 true 로 설정하여,
     * 하위 댓글 여부 표시
     */
    public void setTrueHasReply() {
        this.hasReply = true;
    }

    private Comment(Comment refComment, PostId postId, CommentAuthor author, String content, boolean isSecrete) {
        this.refComment = refComment;
        this.postId = postId;
        this.author = author;
        this.content = content;
        this.isSecrete = isSecrete;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Comment create(CommentAuthor author, Comment refComment, PostId postId, String content, boolean isSecrete) {
        Comment comment = new Comment(refComment, postId, author, content, isSecrete);

        if (comment.getRefComment() != null)
            comment.getRefComment().setTrueHasReply();

        return comment;
    }

    @Override
    public UserId getUserId() {
        return this.getAuthor().getUserId();
    }
}
