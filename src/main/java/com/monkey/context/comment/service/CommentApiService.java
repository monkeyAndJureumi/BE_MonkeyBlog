package com.monkey.context.comment.service;

import com.monkey.context.comment.domain.CommentId;
import com.monkey.context.comment.dto.CommentDto;
import com.monkey.context.comment.dto.CommentResponseDto;
import com.monkey.context.comment.infra.repository.CommentRepository;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentApiService {
    private final CommentRepository commentRepository;

    public CommentResponseDto selectByPostId(final MemberId memberId, final PostId postId) {
        List<CommentDto> comments = commentRepository.findAllByPostId(postId);
        setSecretComment(comments, memberId);
        return new CommentResponseDto(comments);
    }

    public CommentResponseDto selectByCommentId(final MemberId memberId, final CommentId commentId) {
        List<CommentDto> comments = commentRepository.findAllByRefCommentId(commentId);
        setSecretComment(comments, memberId);
        return new CommentResponseDto(comments);
    }

    private void setSecretComment(List<CommentDto> comments, MemberId memberId) {
        comments.forEach(comment -> {
            if (comment.isSecrete() && !comment.getAuthor().equals(memberId) && !comment.getRefUserId().equals(memberId))
                comment.setSecreteComment();
        });
    }
}
