package com.monkey.context.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogCreateDto extends UserSessionDto {
    @JsonProperty("blog_name")
    private String name;

    @JsonProperty("description")
    private String description;
}
