package com.monkey.aggregate.comment.entity;

import com.monkey.aggregate.comment.view.CommentUpdateReq;
import com.monkey.aggregate.post.entity.PostId;
import com.monkey.aggregate.user.entity.UserId;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "content")
    private String content;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "post_id"))
    private PostId postId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "author"))
    private UserId userId;

    @Column(name = "hasReply")
    private boolean hasReply;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public void update(CommentUpdateReq req) {
        this.content = req.getContent();
        this.modifiedAt = LocalDateTime.now();
    }

    public void delete() {
        this.content = "삭제된 댓글입니다.";
        this.userId = null;
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

//    public void setFalseHasReply() {
//        this.hasReply = false;
//    }

    private Comment(Comment refComment, PostId postId, UserId userId, String content) {
        this.refComment = refComment;
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.createAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Comment create(UserId userId, Comment refComment, PostId postId, String content) {
        // 부모노드가 null 아닌 경우 자식노드끼리 연관관계가 매핑되어있는 걸로 간주
        if (refComment != null && refComment.getRefComment() != null)
            throw new MonkeyException(ErrorCode.E500);

        Comment comment = new Comment(refComment, postId, userId, content);

        if (comment.getRefComment() != null)
            comment.getRefComment().setTrueHasReply();

        return comment;
    }
}
