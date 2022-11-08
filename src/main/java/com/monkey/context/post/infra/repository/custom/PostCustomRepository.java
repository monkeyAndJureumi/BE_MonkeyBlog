package com.monkey.context.post.infra.repository.custom;

import com.monkey.context.post.domain.Post;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostResponseDto;
import com.monkey.context.temp_post.domain.TempPostId;

import java.util.Optional;

public interface PostCustomRepository {

    Optional<PostResponseDto> selectByPostId(PostId postId);
    void deleteTempPostById(TempPostId tempPostId);

    Optional<Post> findByPostIdWithPostLike(PostId postId);
}
