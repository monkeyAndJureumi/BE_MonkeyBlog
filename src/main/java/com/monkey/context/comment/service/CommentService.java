package com.monkey.context.comment.service;

import com.monkey.context.comment.domain.CommentAuthor;
import com.monkey.context.comment.dto.CommentSaveDto;
import com.monkey.context.comment.dto.CommentUpdateDto;
import com.monkey.context.comment.domain.Comment;
import com.monkey.context.comment.infra.repository.CommentRepository;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.member.domain.MemberId;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PermissionService permissionService;

    public void save(CommentSaveDto dto) {
        if (dto.getMemberId() == null || dto.getPostId() == null)
            throw new MonkeyException(CommonErrorCode.E400);
        Comment refComment = null;

        // 요청 객체의 rootComment 값이 null 아닐 경우 대댓글로 판단하여 루트댓글 로드
        if (dto.getRefCommentId() != null) {
            refComment = commentRepository.findById(dto.getRefCommentId())
                    .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));

            // 대댓글인 경우(refComment != null) 루트 댓글의 refComment 필드는 null 되야함
            if (refComment.getRefComment() != null)
                throw new MonkeyException(CommonErrorCode.E400);
            refComment.setHasReplyTrue();
        }

        Comment comment = Comment.builder()
                .author(new CommentAuthor(dto.getMemberId()))
                .postId(new PostId(dto.getPostId()))
                .refComment(refComment)
                .content(dto.getContent())
                .isSecrete(dto.getIsSecret())
                .build();

        commentRepository.save(comment);
    }

    public void modifyComment(CommentUpdateDto dto) {
        Comment comment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        permissionService.checkPermission(dto.getMemberId(), comment);
        comment.update(dto);
    }

    public void deleteComment(MemberId memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        permissionService.checkPermission(memberId, comment);
        comment.delete();
    }
}
