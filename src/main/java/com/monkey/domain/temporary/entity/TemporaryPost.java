package com.monkey.domain.temporary.entity;

import com.monkey.domain.user.entity.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "temporary_pst")
public class TemporaryPost {
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

    private TemporaryPost(UserId userId, String content) {
        LocalDateTime now = LocalDateTime.now();
        this.postId = new TemporaryPostId(System.currentTimeMillis() + userId.getId().toString());
        this.userId = userId;
        this.content = content;
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public static TemporaryPost create(UserId userId, String content) {
        return new TemporaryPost(userId, content);
    }

    public void update(String content) {
        this.content = content;
        this.modifiedAt = LocalDateTime.now();
    }
}
