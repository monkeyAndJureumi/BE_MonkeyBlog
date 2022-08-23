package com.monkey.aggregate.post.service;

import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.repository.PostRepository;
import com.monkey.aggregate.post.view.PostRes;
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
