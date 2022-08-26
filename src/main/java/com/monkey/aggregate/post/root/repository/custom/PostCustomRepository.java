package com.monkey.aggregate.post.root.repository.custom;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.dto.PostDto;

public interface PostCustomRepository {

    PostDto selectByPostId(PostId postId);
}
