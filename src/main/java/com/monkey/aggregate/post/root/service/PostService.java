package com.monkey.aggregate.post.root.service;

import com.monkey.aggregate.post.root.entity.Post;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.post.root.view.PostSaveReq;
import com.monkey.aggregate.post.root.view.PostDeleteReq;
import com.monkey.aggregate.post.root.view.PostUpdateReq;
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

    public void savePost(final PostSaveReq req) {
        Post post = Post.create(req.getUserId(), req.getContent());
        postRepository.save(post);
    }

    public void modifyPost(final PostUpdateReq req) {
        Post post = postRepository.findByUserIdAndId(req.getUserId(), req.getPostId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E500));
        post.update(req);
    }

    public void deletePost(final PostDeleteReq req) {
        Post post = postRepository.findByUserIdAndId(req.getUserId(), req.getPostId())
                .orElseThrow(() -> new MonkeyException(ErrorCode.E500));
        postRepository.delete(post);
    }
}
