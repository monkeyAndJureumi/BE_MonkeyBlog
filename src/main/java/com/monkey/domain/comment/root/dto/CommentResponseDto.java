package com.monkey.domain.comment.root.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentResponseDto {
    private List<CommentDto> data;

    public CommentResponseDto(List<CommentDto> data) {
        this.data = data;
    }
}
