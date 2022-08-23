package com.monkey.aggregate.comment.domain.service;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.repository.CommentRepository;
import com.monkey.aggregate.comment.view.CommentDeleteReq;
import com.monkey.aggregate.comment.view.CommentSaveReq;
import com.monkey.aggregate.comment.view.CommentUpdateReq;
import com.monkey.aggregate.comment.service.CommentService;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentService commentService;

    @DisplayName("댓글 저장")
    @Test
    public void saveComment() {
        //given
        Long userId = 1L;
        CommentSaveReq req = new CommentSaveReq(1L, null, "댓글");
        req.setUserId(userId);

        doReturn(Optional.ofNullable(Comment.create(new UserId(userId), null, new PostId(req.getPostId()), req.getContent())))
                .when(commentRepository)
                .findById(any(Long.class));

        //when
        commentService.saveComment(req);
        Comment result = commentRepository.findById(1L).orElseThrow();

        //then
        assertEquals(result.getContent(), req.getContent());
        verify(commentRepository, times(1)).findById(any(Long.class));
    }

    @DisplayName("대댓글 저장")
    @Test
    public void saveLowerComment() {
        //given
        Comment comment1 = Comment.create(new UserId(1L), null, new PostId(1L), "댓글");
        ReflectionTestUtils.setField(comment1, "id", 1L);

        Comment comment2 = Comment.create(new UserId(1L), comment1, new PostId(1L), "대댓글");
        ReflectionTestUtils.setField(comment2, "id", 2L);

        doReturn(comment1)
                .doReturn(comment2)
                .when(commentRepository)
                .save(any(Comment.class));

        doReturn(Optional.ofNullable(comment1))
                .doReturn(Optional.ofNullable(comment1))
                .doReturn(Optional.ofNullable(comment2))
                .when(commentRepository)
                .findById(any(Long.class));

        //when
        CommentSaveReq req1 = new CommentSaveReq(1L, null, "댓글");
        req1.setUserId(1L);
        commentService.saveComment(req1);
        Comment result1 = commentRepository.findById(1L).orElseThrow();

        CommentSaveReq req2 = new CommentSaveReq(1L, 1L, "대댓글");
        req2.setUserId(2L);
        commentService.saveComment(req2);
        Comment result2 = commentRepository.findById(2L).orElseThrow();


        //then
        assertEquals(result1.getId(), result2.getRefComment().getId());
        verify(commentRepository, times(3)).findById(any(Long.class));
    }

    @DisplayName("댓글_조회")
    @Test
    public void getComments() {
        //given
        doReturn(comments()).when(commentRepository).findAllByPostIdOrderByCreateAtDesc(new PostId(1L));

        //when
        List<Comment> comments = commentService.getLowerComments(1L);

        //then
        assertEquals(10, comments.size());
        verify(commentRepository, times(1)).findAllByPostIdOrderByCreateAtDesc(any(PostId.class));
    }

    private List<Comment> comments() {
        List<Comment> comments = new ArrayList<>();
        for (var i = 0; i < 10; i++) {
            CommentSaveReq dto = new CommentSaveReq((long) i, null, "댓글" + i);
            comments.add(Comment.create(new UserId(1L), null, new PostId(dto.getPostId()), dto.getContent()));
        }

        return comments;
    }

    @DisplayName("댓글 삭제")
    @Test
    public void deleteComment() {
        //given
        CommentSaveReq dto = new CommentSaveReq(1L, 1L, "대댓글");
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(dto.getPostId()), dto.getContent())))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));

        //when
        String text = "삭제된 댓글입니다.";
        CommentDeleteReq req = new CommentDeleteReq(1L);
        req.setUserId(1L);
        commentService.deleteComment(req);
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(dto.getPostId()), text)))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));
        Comment comment = commentRepository.findByUserIdAndId(new UserId(1L), 1L).orElseThrow();

        //then
        assertEquals(comment.getContent(), text);
    }

    @DisplayName("댓글 수정")
    @Test
    public void updateComment() {
        //given
        CommentSaveReq saveDto = new CommentSaveReq(1L, 1L, "대댓글");
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(saveDto.getPostId()), saveDto.getContent())))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));

        //when
        String text = "수정 후 댓글";
        CommentUpdateReq updateDto = new CommentUpdateReq(1L, text);
        updateDto.setUserId(1L);
        commentService.modifyComment(updateDto);

        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(updateDto.getCommentId()), updateDto.getContent())))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));
        Comment comment = commentRepository.findByUserIdAndId(new UserId(1L), 1L).orElseThrow();


        //then
        assertEquals(comment.getContent(), text);
    }
}
