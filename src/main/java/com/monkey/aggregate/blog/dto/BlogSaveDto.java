package com.monkey.aggregate.blog.dto;

import com.monkey.view.UserSession;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogSaveDto extends UserSession {
    private String blogName;
}
