package com.monkey.aggregate.comment.root.service;

import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.comment.root.repository.CommentRepository;
import com.monkey.aggregate.comment.root.view.ReplyCommentsRes;
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
