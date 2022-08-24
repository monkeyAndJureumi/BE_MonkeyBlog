package com.monkey.aggregate.post.root.service;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.post.root.view.PostRes;
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
