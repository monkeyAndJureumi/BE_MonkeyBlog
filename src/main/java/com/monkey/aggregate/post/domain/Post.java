package com.monkey.aggregate.post.domain;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.post.view.PostUpdateReq;
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
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", length = 1000)
    private String content;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "author"))
    private UserId userId;

//    @ElementCollection
//    @CollectionTable(name = "post_comments", joinColumns = @JoinColumn(name = "id"))
//    @OrderColumn(name = "comment_id")
//    private Set<CommentId> commentIds = new LinkedHashSet<>();
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    private Post(UserId userId, String content) {
        this.userId = userId;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Post create(UserId userId, String content) {
        return new Post(userId, content);
    }

    public void update(PostUpdateReq req) {
        this.content = req.getContent();
        this.modifiedAt = LocalDateTime.now();
    }
}
