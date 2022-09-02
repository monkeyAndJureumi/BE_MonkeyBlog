package com.monkey.aggregate.post.domain;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.aggregate.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post implements PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Embedded
    private PostAuthor author;

    private boolean isSecret;

//    @ElementCollection
//    @CollectionTable(name = "post_comments", joinColumns = @JoinColumn(name = "id"))
//    @OrderColumn(name = "comment_id")
//    private Set<CommentId> commentIds = new LinkedHashSet<>();
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    private Post(PostAuthor author, String content, boolean isSecret) {
        this.author = author;
        this.content = content;
        this.isSecret = isSecret;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static Post create(PostAuthor postAuthor, String content, boolean isSecret) {
        return new Post(postAuthor, content, isSecret);
    }

    public void update(String content, boolean isSecret) {
        this.content = content;
        this.isSecret = isSecret;
        this.modifiedAt = LocalDateTime.now();
    }

    @Override
    public UserId getUserId() {
        return this.author.getUserId();
    }
}
