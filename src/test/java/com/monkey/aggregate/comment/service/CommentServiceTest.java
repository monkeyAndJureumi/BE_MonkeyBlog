package com.monkey.aggregate.comment.service;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.domain.CommentAuthor;
import com.monkey.aggregate.comment.dto.CommentSaveDto;
import com.monkey.aggregate.comment.dto.CommentUpdateDto;
import com.monkey.aggregate.comment.infra.repository.CommentRepository;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.permission.service.PermissionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PermissionService permissionService;

    @DisplayName("댓글 삽입 기능")
    @Test
    public void saveComment() {
        //given
        CommentSaveDto dto = new CommentSaveDto(1L, null, "Test Comment", false);
        dto.setSession("test");

        //when
        commentService.save(dto);

        //verify
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @DisplayName("대댓글 삽입 기능")
    @Test
    public void saveReply() {
        //given
        CommentSaveDto dto = new CommentSaveDto(1L, 1L, "Test Reply Comment", false);
        dto.setSession("test");
        doReturn(Optional.of(getComment())).when(commentRepository).findById(dto.getRefCommentId());

        //when
        commentService.save(dto);

        //verify
        verify(commentRepository, times(1)).findById(dto.getRefCommentId());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @DisplayName("댓글 수정")
    @Test
    public void modifyComment() {
        //given
        CommentUpdateDto dto = new CommentUpdateDto(1L, "Test Modify Comment", false);
        dto.setSession("test");
        Comment comment = getComment();
        doReturn(Optional.of(comment)).when(commentRepository).findById(1L);
        //when
        commentService.modifyComment(dto);

        //verify
        verify(commentRepository, times(1)).findById(dto.getCommentId());
        verify(permissionService, times(1)).checkPermission(dto.getUserId(), comment);
    }

    @DisplayName("댓글 삭제")
    @Test
    public void deleteComment() {
        //given
        UserId userId = new UserId(1L);
        Long commentId = 1L;
        Comment comment = getComment();
        doReturn(Optional.of(comment)).when(commentRepository).findById(1L);

        //when
        commentService.deleteComment(userId, commentId);

        //verify
        verify(commentRepository, times(1)).findById(commentId);
    }

    private Comment getComment() {
        Comment comment = Comment.builder()
                .author(new CommentAuthor(new UserId(1L)))
                .postId(new PostId(1L))
                .refComment(null)
                .content("Test Comment")
                .isSecrete(false)
                .build();
        return comment;
    }
}
