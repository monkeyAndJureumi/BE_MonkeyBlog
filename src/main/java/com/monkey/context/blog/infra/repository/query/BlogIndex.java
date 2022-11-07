package com.monkey.context.blog.infra.repository.query;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogIndex {
    @JsonProperty("blog_id")
    private String id;
    @JsonProperty("blog_name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public BlogIndex(String id, String name, String description, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}
