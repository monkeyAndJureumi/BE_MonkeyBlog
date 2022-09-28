package com.monkey.aggregate.post_temp.domain;

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
@Entity @Table(name = "post_temp")
public class PostTemp implements PermissionEntity {
    @EmbeddedId
    private PostTempId id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Embedded
    private UserId userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public PostTemp(String id, String content, UserId userId, LocalDateTime createdAt) {
        this.id = new PostTempId(id);
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public void update(String content) {
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }
}
