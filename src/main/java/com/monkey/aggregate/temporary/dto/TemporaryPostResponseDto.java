package com.monkey.aggregate.temporary.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TemporaryPostResponseDto {
    private String postId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
