package com.monkey.aggregate.blog.dto;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSaveDto extends UserSessionDto {
    private String blogName;
}
