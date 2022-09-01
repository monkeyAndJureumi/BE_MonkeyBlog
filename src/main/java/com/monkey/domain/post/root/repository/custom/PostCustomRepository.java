package com.monkey.domain.post.root.repository.custom;

import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.post.root.dto.PostResponseDto;

public interface PostCustomRepository {

    PostResponseDto selectByPostId(PostId postId);
}
