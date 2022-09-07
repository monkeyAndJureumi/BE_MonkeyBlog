package com.monkey.aggregate.temporary.domain;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.permission.implement.PermissionEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "temporary_post")
public class TemporaryPost implements PermissionEntity {
    @EmbeddedId
    private TemporaryPostId postId;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    private UserId userId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Builder
    public TemporaryPost(UserId userId, String content) {
        LocalDateTime now = LocalDateTime.now();
        this.postId = new TemporaryPostId(createId(userId));
        this.userId = userId;
        this.content = content;
        this.createdAt = now;
        this.modifiedAt = now;
    }

    private String createId(UserId userId) {
        return System.currentTimeMillis() + "-" + userId.toString();
    }

    public void update(String content) {
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
