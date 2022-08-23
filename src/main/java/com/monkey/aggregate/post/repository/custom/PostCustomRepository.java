package com.monkey.aggregate.post.repository.custom;

import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.view.PostRes;

public interface PostCustomRepository {

    PostRes getResponseByPostId(PostId postId);
}
