package com.monkey.context.comment.infra.repository.custom;

import com.monkey.context.comment.domain.CommentId;
import com.monkey.context.comment.dto.CommentDto;
import com.monkey.context.post.domain.PostId;

import java.util.List;

public interface CommentCustomRepository {
    List<CommentDto> findAllByPostId(PostId postId);
    List<CommentDto> findAllByRefCommentId(CommentId commentId);
}
