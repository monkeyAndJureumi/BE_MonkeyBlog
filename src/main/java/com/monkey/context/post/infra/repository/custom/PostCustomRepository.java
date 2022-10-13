package com.monkey.context.post.infra.repository.custom;

import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostResponseDto;

import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> selectByPostId(PostId postId);
}
