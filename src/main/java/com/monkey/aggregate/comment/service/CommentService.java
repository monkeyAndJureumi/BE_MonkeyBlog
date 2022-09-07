package com.monkey.aggregate.comment.service;

import com.monkey.aggregate.comment.domain.CommentAuthor;
import com.monkey.aggregate.comment.dto.CommentSaveDto;
import com.monkey.aggregate.comment.dto.CommentUpdateDto;
import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.infra.repository.CommentRepository;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PermissionService permissionService;

    public void save(CommentSaveDto dto) {
        if (dto.getUserId() == null || dto.getPostId() == null)
            throw new MonkeyException(MonkeyErrorCode.E400);
        Comment refComment = null;

        // 요청 객체의 rootComment 값이 null 아닐 경우 대댓글로 판단하여 루트댓글 로드
        if (dto.getRefCommentId() != null) {
            refComment = commentRepository.findById(dto.getRefCommentId()).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E400, HttpStatus.BAD_REQUEST));

            // 대댓글인 경우(refComment != null) 루트 댓글의 refComment 필드는 null 되야함
            if (refComment.getRefComment() != null)
                throw new MonkeyException(MonkeyErrorCode.E400, HttpStatus.BAD_REQUEST);
            refComment.setHasReplyTrue();
        }

        Comment comment = Comment.builder()
                .author(new CommentAuthor(dto.getUserId()))
                .postId(new PostId(dto.getPostId()))
                .refComment(refComment)
                .content(dto.getContent())
                .isSecrete(dto.getIsSecret())
                .build();

        commentRepository.save(comment);
    }

    public void modifyComment(CommentUpdateDto dto) {
        Comment comment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E200, HttpStatus.NOT_FOUND));
        permissionService.checkPermission(dto.getUserId(), comment);
        comment.update(dto);
    }

    public void deleteComment(UserId userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E200, HttpStatus.NOT_FOUND));
        permissionService.checkPermission(userId, comment);
        comment.delete();
    }
}
