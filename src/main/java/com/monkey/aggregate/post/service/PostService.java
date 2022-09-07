package com.monkey.aggregate.post.service;

import com.monkey.aop.permission.service.PermissionService;
import com.monkey.aggregate.post.domain.PostAuthor;
import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.infra.repository.PostRepository;
import com.monkey.aggregate.post.dto.PostSaveDto;
import com.monkey.aggregate.post.dto.PostUpdateDto;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final PermissionService permissionService;

    public void savePost(final PostSaveDto dto) {
        Post post = Post.builder()
                .author(new PostAuthor(dto.getUserId()))
                .content(dto.getContent())
                .isSecret(dto.getIsSecrete())
                .build();
        postRepository.save(post);
    }

    public void modifyPost(final PostUpdateDto dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));
        permissionService.checkPermission(dto.getUserId(), post);
        post.update(dto.getContent(), dto.getIsSecret());
    }

    public void deletePost(final UserId userId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));
        permissionService.checkPermission(userId, post);
        postRepository.delete(post);
    }
}
