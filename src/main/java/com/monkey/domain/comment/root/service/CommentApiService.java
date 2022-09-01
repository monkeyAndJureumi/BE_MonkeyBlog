package com.monkey.domain.comment.root.service;

import com.monkey.domain.comment.root.dto.CommentDto;
import com.monkey.domain.comment.root.dto.CommentResponseDto;
import com.monkey.domain.comment.root.entity.CommentId;
import com.monkey.domain.comment.root.repository.CommentRepository;
import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.user.root.entity.UserId;
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
        List<CommentDto> comments = commentRepository.findAllByRefComment(userId, commentId);
        return new CommentResponseDto(comments);
    }
}
