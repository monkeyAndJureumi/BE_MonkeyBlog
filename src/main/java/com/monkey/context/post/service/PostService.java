package com.monkey.context.post.service;

import com.monkey.context.grant.service.GrantService;
import com.monkey.context.post.domain.*;
import com.monkey.context.post.infra.repository.PostRepository;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostUpdateDto;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.dto.RegisteredPost;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final GrantService grantService;
    private final ApplicationEventPublisher eventPublisher;

    public PostId save(final MemberId memberId, final PostSaveDto dto) {
        Post post = new Post(new PostAuthor(memberId), dto);
        postRepository.save(post);
        eventPublisher.publishEvent(new RegisteredPost(memberId, dto.getTempPostId()));
        return new PostId(post.getId());
    }

    public void modify(final MemberId memberId, final Long postId, final PostUpdateDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        grantService.authorize(memberId, post.getAuthor().getMemberId());
        post.update(dto);
    }

    public void delete(final MemberId memberId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400));
        grantService.authorize(memberId, post.getAuthor().getMemberId());
        postRepository.delete(post);
    }
}
