package com.monkey.aggregate.blog.domain;

import com.monkey.aggregate.user.domain.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "blog")
public class Blog {
    @EmbeddedId
    private BlogId id;

    @Embedded
    private UserId userId;

    @Column(name = "name")
    private String blogName;

    private Blog(BlogId id, UserId userId, String blogName) {
        this.id = id;
        this.userId = userId;
        this.blogName = blogName;
    }

    public static Blog create(BlogId id, UserId userId, String blogName) {
        return new Blog(id, userId, blogName);
    }
}
