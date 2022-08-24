package com.monkey.aggregate.post.view;

import com.monkey.aggregate.comment.dto.CommentDto;

import com.monkey.aggregate.post.dto.PostDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostRes {
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentDto> comments;

    public PostRes(PostDto postDto, List<CommentDto> commentResponseDtoList) {
        this.content = postDto.getContent();
        this.createdAt = postDto.getCreatedAt();
        this.modifiedAt = postDto.getModifiedAt();
        this.comments = commentResponseDtoList.stream()
                .map(comment -> new CommentDto(comment.getContent(), comment.getCreatedAt(), comment.isHasReply()))
                .collect(Collectors.toList());
    }
}
