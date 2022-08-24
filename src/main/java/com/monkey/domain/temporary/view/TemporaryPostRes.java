package com.monkey.domain.temporary.view;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TemporaryPostRes {
    private String postId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
