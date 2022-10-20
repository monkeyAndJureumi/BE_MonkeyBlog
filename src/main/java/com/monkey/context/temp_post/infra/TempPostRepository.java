package com.monkey.context.temp_post.infra;

import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempPostRepository extends JpaRepository<TempPost, TempPostId> {
}
