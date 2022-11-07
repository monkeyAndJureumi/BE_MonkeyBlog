package com.monkey.context.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.blog.infra.repository.query.BlogIndex;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogIndexResponseDto {
    @JsonProperty("data")
    private List<BlogIndex> data;

    public BlogIndexResponseDto(List<BlogIndex> data) {
        this.data = data;
    }
}
