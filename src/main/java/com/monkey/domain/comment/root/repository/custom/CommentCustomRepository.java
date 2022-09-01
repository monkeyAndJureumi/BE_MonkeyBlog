package com.monkey.domain.comment.root.repository.custom;

import com.monkey.domain.comment.root.dto.CommentDto;
import com.monkey.domain.comment.root.entity.CommentId;
import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.user.root.entity.UserId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentDto> findAllByPostId(UserId userId, PostId postId);
    List<CommentDto> findAllByRefComment(UserId userId, CommentId commentId);
}
