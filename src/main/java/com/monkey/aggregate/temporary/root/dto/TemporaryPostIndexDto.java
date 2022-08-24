package com.monkey.aggregate.temporary.root.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemporaryPostIndexDto {
    private String postId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TemporaryPostIndexDto(String postId, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.postId = postId;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
