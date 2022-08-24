package com.monkey.domain.comment.service;

import com.monkey.domain.comment.entity.Comment;
import com.monkey.domain.comment.entity.CommentId;
import com.monkey.domain.comment.repository.CommentRepository;
import com.monkey.domain.comment.view.ReplyCommentsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentApiService {
    private final CommentRepository commentRepository;

    public ReplyCommentsRes getReplyComments(CommentId commentId) {
        Comment comment = commentRepository.getReferenceById(commentId.getId());
        List<Comment> comments = commentRepository.findAllByRefComment(comment);

        return new ReplyCommentsRes(comments);
    }

}
