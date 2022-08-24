package com.monkey.aggregate.post.root.repository.custom;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.view.PostRes;

public interface PostCustomRepository {

    PostRes getResponseByPostId(PostId postId);
}
