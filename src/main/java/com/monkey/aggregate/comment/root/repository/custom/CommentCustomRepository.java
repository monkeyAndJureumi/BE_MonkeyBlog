package com.monkey.aggregate.comment.root.repository.custom;

import com.monkey.aggregate.comment.root.dto.CommentResponseDto;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentResponseDto> findAllByPostId(UserId userId, PostId postId);
    List<CommentResponseDto> findAllByRefComment(UserId userId, CommentId commentId);
}
