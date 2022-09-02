package com.monkey.aggregate.temporary.service;

import com.monkey.aggregate.temporary.repository.TemporaryPostRepository;
import com.monkey.aggregate.temporary.view.TemporaryPostRes;
import com.monkey.aggregate.temporary.view.TemporaryPostSaveReq;
import com.monkey.aggregate.temporary.view.TemporaryPostUpdateReq;
import com.monkey.aggregate.temporary.domain.TemporaryPost;
import com.monkey.aggregate.temporary.domain.TemporaryPostId;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemporaryPostService {
    private final TemporaryPostRepository temporaryPostRepository;

    public TemporaryPostRes getPost(final TemporaryPostId postId, UserId userId) {
        TemporaryPost post = temporaryPostRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new MonkeyException(ErrorCode.E100));

        return new TemporaryPostRes(post.getPostId().getId(), post.getContent(), post.getCreatedAt(), post.getModifiedAt());
    }

    /**
     * @param req 임시 게시글 저장 객체
     * @return 엔티티 인덱스 값
     */
    public String savePost(TemporaryPostSaveReq req) {
        TemporaryPost temporaryPost = TemporaryPost.create(req.getUserId(), req.getContent());
        return temporaryPostRepository.save(temporaryPost).getPostId().getId();
    }

    /**
     * @param req 임시 게시글 업데이트 요청객체(유저아이디, 엔티티 인덱스 값)
     */
    public void updatePost(TemporaryPostUpdateReq req) {
        TemporaryPost temporaryPost = temporaryPostRepository.findById(new TemporaryPostId(req.getPostId()))
                .orElseThrow(() -> new MonkeyException(ErrorCode.E100));
        temporaryPost.update(req.getContent());
    }

    /**
     * @param postId 임시 게시글 인덱스
     * @param userId 유저 인덱스
     */
    public void deletePost(TemporaryPostId postId, UserId userId) {
        TemporaryPost temporaryPost = temporaryPostRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new MonkeyException(ErrorCode.E100));
        temporaryPostRepository.delete(temporaryPost);
    }
}
