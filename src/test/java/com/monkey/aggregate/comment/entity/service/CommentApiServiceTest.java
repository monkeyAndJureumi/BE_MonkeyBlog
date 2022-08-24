package com.monkey.aggregate.comment.entity.service;

import com.monkey.aggregate.comment.entity.Comment;
import com.monkey.aggregate.comment.entity.CommentId;
import com.monkey.aggregate.comment.repository.CommentRepository;
import com.monkey.aggregate.comment.service.CommentApiService;
import com.monkey.aggregate.comment.view.ReplyCommentsRes;
import com.monkey.aggregate.post.entity.PostId;
import com.monkey.aggregate.user.entity.UserId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CommentApiServiceTest {
    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentApiService commentApiService;

    @Test
    public void replyCommentsTest() {
        //given
        Comment comment = Comment.create(new UserId(1L), null, new PostId(1L), "comment");
        List<Comment> replyComments = replyComments(comment);
        doReturn(comment)
                .when(commentRepository)
                .getReferenceById(any(Long.class));
        doReturn(replyComments)
                .when(commentRepository)
                .findAllByRefComment(comment);

        //when
        ReplyCommentsRes res = commentApiService.getReplyComments(new CommentId(1L));

        //then
        assertEquals(100, res.getComments().size());

    }

    private List<Comment> replyComments(Comment comment) {
        List<Comment> comments = new ArrayList<>();
        for (var i = 0; i < 100; i++) {
            comments.add(Comment.create(new UserId(1L), comment, new PostId(1L), "reply" + (i + 1)));
        }

        return comments;
    }
}
