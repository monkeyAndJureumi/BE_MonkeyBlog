package com.monkey.aggregate.post.service;

import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.dto.PostSaveDto;
import com.monkey.aggregate.post.infra.repository.PostRepository;
import com.monkey.aop.permission.service.PermissionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    // 의존성 Mock 객체 주입
    @Mock
    private PostRepository postRepository;

    @Mock
    private PermissionService permissionService;

    // 테스트 대상 Mock 객체 주입
    @InjectMocks
    private PostService postService;

    @DisplayName("게시글 저장")
    @Test
    public void savePost() {
        //given
        PostSaveDto dto = new PostSaveDto("Test Content", false);

        //when
        postService.savePost(dto);

        //verify
        verify(postRepository, times(1)).save(any(Post.class));
    }

//    @DisplayName("게시글 수정")
//    @Test
//    public void modifiedPost() {
//        //given
//        PostUpdateDto updateDto = new PostUpdateDto(1L, "Modified Content", false, PostStatus.DEACTIVATE);
//        Post post = Post.builder()
//                .author(new PostAuthor(new UserId(1L)))
//                .content("Test Content")
//                .isSecret(false)
//                .build();
//        doReturn(Optional.of(post))
//                .when(postRepository).findById(any(Long.class));
//
//        //when
//        postService.modifyPost(updateDto);
//
//        //verify
//        verify(postRepository, times(1)).findById(any(Long.class));
//        verify(permissionService, times(1)).checkPermission(updateDto.getUserId(), post);
//    }

//    @DisplayName("게시글 삭제")
//    @Test
//    public void deletePost() {
//        //given
//        UserId userId = new UserId(1L);
//        PostId postId = new PostId(1L);
//
//        Post post = Post.builder()
//                .author(new PostAuthor(userId))
//                .content("Test Content")
//                .isSecret(false)
//                .build();
//
//        doReturn(Optional.of(post))
//                .when(postRepository).findById(postId.getId());
//
//        //when
//        postService.deletePost(userId, postId);
//
//        //verify
//        verify(postRepository, times(1)).findById(postId.getId());
//        verify(postRepository, times(1)).delete(post);
//    }
}
