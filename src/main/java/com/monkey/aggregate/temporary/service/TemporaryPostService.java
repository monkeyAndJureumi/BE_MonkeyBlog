package com.monkey.aggregate.temporary.service;

import com.monkey.aggregate.temporary.repository.TemporaryPostRepository;
import com.monkey.aggregate.temporary.dto.TemporaryPostResponseDto;
import com.monkey.aggregate.temporary.dto.TemporaryPostSaveDto;
import com.monkey.aggregate.temporary.dto.TemporaryPostUpdateDto;
import com.monkey.aggregate.temporary.domain.TemporaryPost;
import com.monkey.aggregate.temporary.domain.TemporaryPostId;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemporaryPostService {
    private final TemporaryPostRepository temporaryPostRepository;
    private final PermissionService permissionService;

    public TemporaryPostResponseDto getPost(final TemporaryPostId postId, UserId userId) {
        TemporaryPost temporaryPost = temporaryPostRepository.findById(postId)
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));
        permissionService.checkPermission(userId, temporaryPost);
        return new TemporaryPostResponseDto(temporaryPost.getPostId().getId(), temporaryPost.getContent(), temporaryPost.getCreatedAt(), temporaryPost.getModifiedAt());
    }

    /**
     * @param dto 임시 게시글 저장 객체
     * @return 엔티티 인덱스 값
     */
    public String savePost(TemporaryPostSaveDto dto) {
        TemporaryPost temporaryPost = TemporaryPost.builder().userId(dto.getUserId()).content(dto.getContent()).build();
        return temporaryPostRepository.save(temporaryPost).getPostId().getId();
    }

    /**
     * @param dto 임시 게시글 업데이트 요청객체(유저아이디, 엔티티 인덱스 값)
     */
    public void updatePost(TemporaryPostUpdateDto dto) {
        TemporaryPost temporaryPost = temporaryPostRepository.findById(new TemporaryPostId(dto.getPostId()))
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));
        permissionService.checkPermission(dto.getUserId(), temporaryPost);
        temporaryPost.update(dto.getContent());
    }

    /**
     * @param postId 임시 게시글 인덱스
     * @param userId 유저 인덱스
     */
    public void deletePost(TemporaryPostId postId, UserId userId) {
        TemporaryPost temporaryPost = temporaryPostRepository.findById(postId)
                .orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));
        permissionService.checkPermission(userId, temporaryPost);
        temporaryPostRepository.delete(temporaryPost);
    }
}
