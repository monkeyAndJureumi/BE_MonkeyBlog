package com.monkey.aggregate.comment.infra.repository.custom;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentDto> findAllByPostId(PostId postId);
    List<CommentDto> findAllByRefCommentId(CommentId commentId);
}
