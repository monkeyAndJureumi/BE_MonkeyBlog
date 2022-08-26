package com.monkey.aggregate.post.root.service;

import com.monkey.aop.permission.service.PermissionService;
import com.monkey.aggregate.post.root.entity.Post;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.post.root.dto.PostSaveDto;
import com.monkey.aggregate.post.root.dto.PostUpdateDto;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.exception.ErrorCode;
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
        Post post = Post.create(dto.getUserId(), dto.getContent(), dto.getIsSecrete());
        postRepository.save(post);
    }

    public void modifyPost(final PostUpdateDto dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E100));
        permissionService.checkPermission(dto.getUserId(), post);
        post.update(dto.getContent(), dto.getIsSecret());
    }

    public void deletePost(final UserId userId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E100));
        permissionService.checkPermission(userId, post);
        postRepository.delete(post);
    }
}
