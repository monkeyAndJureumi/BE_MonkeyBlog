package com.monkey.aggregate.blog.entity;

import com.monkey.aggregate.user.root.entity.UserId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "blog")
public class Blog {
    @Id
    private String id;

    @Embedded
    private UserId userId;

    @Column(name = "name")
    private String blogName;

    private Blog(String id, UserId userId, String blogName) {
        this.id = id;
        this.userId = userId;
        this.blogName = blogName;
    }

    public static Blog create(String id, UserId userId, String blogName) {
        return new Blog(id, userId, blogName);
    }
}
