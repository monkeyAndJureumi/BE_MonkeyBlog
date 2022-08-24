package com.monkey.domain.post.repository.custom;

import com.monkey.domain.post.entity.PostId;
import com.monkey.domain.post.view.PostRes;

public interface PostCustomRepository {

    PostRes getResponseByPostId(PostId postId);
}
