package com.monkey.aggregate.comment.repository.custom;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentDto> findAllByPostId(UserId userId, PostId postId);
    List<CommentDto> findAllByRefComment(UserId userId, CommentId commentId);
}
