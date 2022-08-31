package com.monkey.aggregate.comment.root.repository.custom;

import com.monkey.aggregate.comment.root.dto.CommentDto;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentDto> findAllByPostId(UserId userId, PostId postId);
    List<CommentDto> findAllByRefComment(UserId userId, CommentId commentId);
}
