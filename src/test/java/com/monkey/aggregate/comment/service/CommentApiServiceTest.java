package com.monkey.aggregate.comment.service;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.comment.dto.CommentResponseDto;
import com.monkey.aggregate.comment.infra.repository.CommentRepository;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentApiServiceTest {
    @InjectMocks
    private CommentApiService commentApiService;

    @Mock
    private CommentRepository commentRepository;

    @DisplayName("댓글 조회")
    @Test
    public void selectComments() {
        //given
        PostId postId = new PostId(1L);
        UserId userId = new UserId(1L);
        List<CommentDto> comments = getCommentList();
        doReturn(comments).when(commentRepository).findAllByPostId(postId);
        //when
        CommentResponseDto result = commentApiService.selectByPostId(userId, postId);

        //then
        assertEquals(100, result.getData().size());

        //verify
        verify(commentRepository, times(1)).findAllByPostId(postId);
    }

    @DisplayName("비밀댓글 조회")
    @Test
    public void selectSecreteComments() {
        //given
        PostId postId = new PostId(1L);
        UserId postAuthor = new UserId(1L);
        List<CommentDto> comments = getCommentList();
        doReturn(comments).when(commentRepository).findAllByPostId(postId);
        //when
        CommentResponseDto selectByPostAuthor = commentApiService.selectByPostId(postAuthor, postId);


        //then
        assertEquals("Test Content", selectByPostAuthor.getData().get(0).getContent());

        //verify
        verify(commentRepository, times(1)).findAllByPostId(postId);
    }

    @DisplayName("비밀댓글 조회 실패")
    @Test
    public void selectSecretCommentsFail() {
        //given
        PostId postId = new PostId(1L);
        UserId anonymous = new UserId(null);
        List<CommentDto> comment = getCommentList();
        doReturn(comment).when(commentRepository).findAllByPostId(postId);

        //when
        CommentResponseDto selectByAnonymous = commentApiService.selectByPostId(anonymous, postId);

        //then
        assertEquals("비밀 댓글입니다.", selectByAnonymous.getData().get(0).getContent());

        //verify
        verify(commentRepository, times(1)).findAllByPostId(postId);
    }

    @DisplayName("답글 조회")
    @Test
    public void selectReplyComments() {
        //given
        CommentId commentId = new CommentId(1L);
        UserId userId = new UserId(1L);
        List<CommentDto> comments = getCommentList();
        doReturn(comments).when(commentRepository).findAllByRefCommentId(commentId);

        //when
        CommentResponseDto result = commentApiService.selectByCommentId(userId, commentId);

        //then
        assertEquals(100, result.getData().size());
        verify(commentRepository, times(1)).findAllByRefCommentId(commentId);
    }

    @DisplayName("비밀 답글 조회")
    @Test
    public void selectSecretReplyComments() {
        //given
        CommentId commentId = new CommentId(1L);
        UserId userId = new UserId(1L);
        List<CommentDto> comments = getCommentList();
        doReturn(comments).when(commentRepository).findAllByRefCommentId(commentId);

        //when
        CommentResponseDto result = commentApiService.selectByCommentId(userId, commentId);

        //then
        assertEquals("Test Content", result.getData().get(0).getContent());
        verify(commentRepository, times(1)).findAllByRefCommentId(commentId);
    }

    @DisplayName("비밀 답글 조회 실패")
    @Test
    public void setSecretReplyCommentsFail() {
        //given
        CommentId commentId = new CommentId(1L);
        UserId userId = new UserId(null);
        List<CommentDto> comments = getCommentList();
        doReturn(comments).when(commentRepository).findAllByRefCommentId(commentId);

        //when
        CommentResponseDto result = commentApiService.selectByCommentId(userId, commentId);

        //then
        assertEquals("비밀 댓글입니다.", result.getData().get(0).getContent());
        verify(commentRepository, times(1)).findAllByRefCommentId(commentId);
    }

    private List<CommentDto> getCommentList() {
        List<CommentDto> result = new ArrayList<>();
        for (long i = 0; i < 2; i++) {
            result.add(new CommentDto(1L, 2L, "Test Name", i, "Test Content", LocalDateTime.now(), false, true));
        }

        return result;
    }
}
