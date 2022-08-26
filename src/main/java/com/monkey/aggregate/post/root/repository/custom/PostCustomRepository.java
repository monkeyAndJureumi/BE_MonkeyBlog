package com.monkey.aggregate.post.root.repository.custom;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.dto.PostResponseDto;

public interface PostCustomRepository {

    PostResponseDto selectByPostId(PostId postId);
}
