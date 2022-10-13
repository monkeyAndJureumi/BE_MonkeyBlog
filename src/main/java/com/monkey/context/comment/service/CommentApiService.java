package com.monkey.context.comment.service;

import com.monkey.context.comment.domain.CommentId;
import com.monkey.context.comment.dto.CommentDto;
import com.monkey.context.comment.dto.CommentResponseDto;
import com.monkey.context.comment.infra.repository.CommentRepository;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.user.domain.UserId;
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
        List<CommentDto> comments = commentRepository.findAllByPostId(postId);
        setSecretComment(comments, userId);
        return new CommentResponseDto(comments);
    }

    public CommentResponseDto selectByCommentId(final UserId userId, final CommentId commentId) {
        List<CommentDto> comments = commentRepository.findAllByRefCommentId(commentId);
        setSecretComment(comments, userId);
        return new CommentResponseDto(comments);
    }

    private void setSecretComment(List<CommentDto> comments, UserId userId) {
        comments.forEach(comment -> {
            if (comment.isSecrete() && !comment.getAuthor().equals(userId) && !comment.getRefUserId().equals(userId))
                comment.setSecreteComment();
        });
    }
}
