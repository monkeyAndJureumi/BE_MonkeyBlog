package com.monkey.aggregate.post.infra.repository.custom;

import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.dto.PostResponseDto;

public interface PostCustomRepository {

    PostResponseDto selectByPostId(PostId postId);
}
