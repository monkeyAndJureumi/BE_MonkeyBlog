package com.monkey.aggregate.comment.root.service;

import com.monkey.aggregate.comment.root.dto.CommentSaveDto;
import com.monkey.aggregate.comment.root.dto.CommentUpdateDto;
import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.repository.CommentRepository;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PermissionService permissionService;


    @Transactional
    public void saveComment(CommentSaveDto dto) {
        if (dto.getUserId() == null || dto.getPostId() == null)
            throw new MonkeyException(ErrorCode.E400);
        Comment refComment = null;
        // 요청 객체의 rootComment 값이 null 아닐 경우 대댓글로 판단
        if (dto.getRefCommentId() != null)
            // 루트댓글이 없는 경우 예외 발생
            refComment = commentRepository.findById(dto.getRefCommentId()).orElseThrow(() -> new MonkeyException(ErrorCode.E400, HttpStatus.BAD_REQUEST));

        // 대댓글인 경우(refComment != null) 루트 댓글의 refComment 필드는 null 되야함
        if (refComment != null && refComment.getRefComment() != null)
            throw new MonkeyException(ErrorCode.E400, HttpStatus.BAD_REQUEST);

        Comment comment = Comment.create(dto.getUserId(), refComment, new PostId(dto.getPostId()), dto.getContent(), dto.getIsSecret());

        commentRepository.save(comment);
    }

    @Transactional
    public void modifyComment(CommentUpdateDto dto) {
        Comment comment = commentRepository.findById(dto.getCommentId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E200, HttpStatus.NOT_FOUND));
        permissionService.checkPermission(dto.getUserId(), comment);
        comment.update(dto);
    }

    @Transactional
    public void deleteComment(UserId userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new MonkeyException(ErrorCode.E200, HttpStatus.NOT_FOUND));
        permissionService.checkPermission(userId, comment);
        comment.delete();
    }

    public List<Comment> getLowerComments(Long postId) {
        return commentRepository.findAllByPostIdAndRefCommentIsNullOrderByCreatedAtDesc(new PostId(postId));
    }
}
