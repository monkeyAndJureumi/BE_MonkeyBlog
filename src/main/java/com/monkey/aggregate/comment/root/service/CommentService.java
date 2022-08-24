package com.monkey.aggregate.comment.root.service;

import com.monkey.aggregate.comment.root.view.CommentDeleteReq;
import com.monkey.aggregate.comment.root.view.CommentSaveReq;
import com.monkey.aggregate.comment.root.view.CommentUpdateReq;
import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.repository.CommentRepository;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(CommentSaveReq req) {
        Comment refComment = null;
        if (req.getRootCommentId() != null)
            refComment = commentRepository.findById(req.getRootCommentId()).orElseThrow(() -> new MonkeyException(ErrorCode.E200));
        Comment comment = Comment.create(req.getUserId(), refComment, new PostId(req.getPostId()), req.getContent());

        commentRepository.save(comment);
    }

    @Transactional
    public void modifyComment(CommentUpdateReq req) {
        Comment comment = commentRepository.findByUserIdAndId(req.getUserId(), req.getCommentId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E200));
        comment.update(req);
    }

    @Transactional
    public void deleteComment(CommentDeleteReq req) {
        Comment comment = commentRepository.findByUserIdAndId(req.getUserId(), req.getCommentId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E200));
        comment.delete();
    }

    public List<Comment> getLowerComments(Long postId) {
        return commentRepository.findAllByPostIdOrderByCreateAtDesc(new PostId(postId));
    }
}
