package com.monkey.aggregate.post.infra.repository.custom;

import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.dto.PostResponseDto;

import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> selectByPostId(PostId postId);
}
