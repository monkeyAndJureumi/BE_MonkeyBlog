package com.monkey.aggregate.comment.service;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.comment.dto.CommentResponseDto;
import com.monkey.aggregate.comment.infra.repository.CommentRepository;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentApiService {
    private final CommentRepository commentRepository;

    public CommentResponseDto selectByPostId(final UserId userId, final PostId postId) {
        List<CommentDto> comments = commentRepository.findAllByPostId(userId, postId);
        return new CommentResponseDto(comments);
    }

    public CommentResponseDto selectByCommentId(final UserId userId, final CommentId commentId) {
        List<CommentDto> comments = commentRepository.findAllByRefCommentId(userId, commentId);
        return new CommentResponseDto(comments);
    }
}
