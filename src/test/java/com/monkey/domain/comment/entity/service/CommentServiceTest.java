package com.monkey.domain.comment.entity.service;

import com.monkey.domain.comment.root.entity.Comment;
import com.monkey.domain.comment.root.repository.CommentRepository;
import com.monkey.domain.comment.root.dto.CommentSaveDto;
import com.monkey.domain.comment.root.dto.CommentUpdateDto;
import com.monkey.domain.comment.root.service.CommentService;
import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.user.root.entity.UserId;
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
        CommentSaveDto req = new CommentSaveDto(1L, null, "댓글", false);
        req.setUserId(userId);

        doReturn(Optional.ofNullable(Comment.create(new UserId(userId), null, new PostId(1L), "댓글", false)))
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
        Comment comment1 = Comment.create(new UserId(1L), null, new PostId(1L), "댓글", false);
        ReflectionTestUtils.setField(comment1, "id", 1L);

        Comment comment2 = Comment.create(new UserId(1L), comment1, new PostId(1L), "대댓글", false);
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
        CommentSaveDto req1 = new CommentSaveDto(1L, null, "댓글", false);
        req1.setUserId(1L);
        commentService.saveComment(req1);
        Comment result1 = commentRepository.findById(1L).orElseThrow();

        CommentSaveDto req2 = new CommentSaveDto(1L, 1L, "대댓글", false);
        req2.setUserId(2L);
        commentService.saveComment(req2);
        Comment result2 = commentRepository.findById(2L).orElseThrow();


        //then
        assertEquals(result1.getId(), result2.getRefComment().getId());
        verify(commentRepository, times(3)).findById(any(Long.class));
    }

    private List<Comment> comments() {
        List<Comment> comments = new ArrayList<>();
        for (var i = 0; i < 10; i++) {
            comments.add(Comment.create(new UserId(1L), null, new PostId((long) i), "댓글" + i, false));
        }

        return comments;
    }

    @DisplayName("댓글 삭제")
    @Test
    public void deleteComment() {
        //given
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(1L), "대댓글", false)))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));

        //when
        String text = "삭제된 댓글입니다.";
        commentService.deleteComment(new UserId(1L), 1L);
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(1L), text, false)))
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
        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(1L), "대댓글", false)))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));

        //when
        String text = "수정 후 댓글";
        CommentUpdateDto updateDto = new CommentUpdateDto(1L, text, false);
        updateDto.setUserId(1L);
        commentService.modifyComment(updateDto);

        doReturn(Optional.of(Comment.create(new UserId(1L), null, new PostId(updateDto.getCommentId()), updateDto.getContent(), false)))
                .when(commentRepository)
                .findByUserIdAndId(any(UserId.class), any(Long.class));
        Comment comment = commentRepository.findByUserIdAndId(new UserId(1L), 1L).orElseThrow();


        //then
        assertEquals(comment.getContent(), text);
    }
}
