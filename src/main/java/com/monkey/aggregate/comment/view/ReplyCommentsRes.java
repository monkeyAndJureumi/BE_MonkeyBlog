package com.monkey.aggregate.comment.view;

import com.monkey.aggregate.comment.entity.Comment;
import com.monkey.aggregate.comment.dto.CommentDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReplyCommentsRes {
    private List<CommentDto> comments;

    public ReplyCommentsRes(List<Comment> comments) {
        this.comments = comments.stream()
                .map(comment -> new CommentDto(comment.getContent(), comment.getCreateAt(), comment.isHasReply()))
                .collect(Collectors.toList());

    }
}
