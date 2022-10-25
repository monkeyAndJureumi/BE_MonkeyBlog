package com.monkey.context.temp_post.domain;

import com.monkey.context.temp_post.dto.TempPostSaveDto;
import com.monkey.context.temp_post.dto.TempPostUpdateDto;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.permission.implement.PermissionEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "post_temp")
public class TempPost implements PermissionEntity {
    @EmbeddedId
    private TempPostId tempPostId;

    @Column(name = "title")
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Embedded
    private TempPostAuthor author;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public TempPost(TempPostSaveDto dto) {
        LocalDateTime now = LocalDateTime.now();
        this.tempPostId = new TempPostId(now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_" + dto.getMemberId().getId());
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = new TempPostAuthor(dto.getMemberId());
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public void update(TempPostUpdateDto dto) {
        this.content = dto.getContent();
        this.modifiedAt = LocalDateTime.now();
    }

    @Override
    public MemberId getMemberId() {
        return author.getMemberId();
    }
}
