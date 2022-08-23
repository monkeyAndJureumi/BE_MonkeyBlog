package com.monkey.aggregate.comment.service;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.repository.CommentRepository;
import com.monkey.aggregate.comment.view.ReplyCommentsRes;
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
