package com.monkey.context.post.service;

import com.monkey.context.grant.service.GrantService;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.domain.Post;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.infra.repository.PostRepository;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostRepository postRepository;
    private final GrantService grantService;

    public void addLike(final MemberId memberId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        grantService.authorize(memberId, post.getAuthor().getMemberId());
        post.addLike(memberId);
    }

    public void deleteLike(MemberId memberId, final PostId postId) {
        Post post = postRepository.findById(postId.getId())
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E404));
        grantService.authorize(memberId, post.getAuthor().getMemberId());
        post.deleteLike(memberId);
    }
}
