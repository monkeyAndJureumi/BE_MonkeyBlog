package com.monkey.aggregate.post_temp.domain;

import com.monkey.aggregate.post_temp.dto.PostTempSaveDto;
import com.monkey.aggregate.post_temp.dto.PostTempUpdateDto;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.permission.implement.PermissionEntity;
import lombok.AccessLevel;
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

    public PostTemp(PostTempSaveDto dto) {
        this.id = new PostTempId(LocalDateTime.now() + "_" + dto.getUserId().getId());
        this.content = dto.getContent();
        this.userId = dto.getUserId();
        this.createdAt = LocalDateTime.now();
    }

    public void update(PostTempUpdateDto dto) {
        this.content = dto.getContent();
        this.createdAt = LocalDateTime.now();
    }
}
