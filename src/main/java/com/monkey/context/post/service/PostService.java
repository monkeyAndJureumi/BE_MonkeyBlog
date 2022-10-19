package com.monkey.context.post.service;

import com.monkey.aop.permission.service.PermissionService;
import com.monkey.context.post.domain.Post;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.infra.repository.PostRepository;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostUpdateDto;
import com.monkey.context.member.domain.MemberId;
import com.monkey.enums.CommonErrorCode;
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
        Post post = new Post(dto);
        postRepository.save(post);
    }

    public void modifyPost(final PostUpdateDto dto) {
        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        permissionService.checkPermission(dto.getMemberId(), post);
        post.update(dto);
    }

    public void deletePost(final MemberId memberId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        permissionService.checkPermission(memberId, post);
        postRepository.delete(post);
    }
}
