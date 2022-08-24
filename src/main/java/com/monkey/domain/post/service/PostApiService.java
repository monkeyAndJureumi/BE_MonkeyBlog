package com.monkey.domain.post.service;

import com.monkey.domain.post.entity.PostId;
import com.monkey.domain.post.repository.PostRepository;
import com.monkey.domain.post.view.PostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostApiService {
    private final PostRepository postRepository;

    public PostRes getPost(PostId postId) {
        return postRepository.getResponseByPostId(postId);
    }
}
