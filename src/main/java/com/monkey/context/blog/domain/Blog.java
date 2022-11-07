package com.monkey.context.blog.domain;

import com.monkey.context.blog.dto.BlogCreateDto;
import com.monkey.context.blog.dto.BlogUpdateDto;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.grant.implement.PermissionEntity;
import com.monkey.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog implements PermissionEntity {
    @EmbeddedId
    private BlogId blogId;

    @Embedded
    private BlogOwner blogOwner;

    @Column(name = "blog_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public Blog (final BlogCreateDto dto) {
        LocalDateTime now = LocalDateTime.now();
        this.blogId = new BlogId(DateTimeUtils.ConvertToString(now, dto.getMemberId().getId()));
        this.blogOwner = new BlogOwner(dto.getMemberId());
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.createdAt = now;
    }

    @Override
    public MemberId getMemberId() {
        return this.blogOwner.getMemberId();
    }

    public void update(final BlogUpdateDto dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
    }
}
