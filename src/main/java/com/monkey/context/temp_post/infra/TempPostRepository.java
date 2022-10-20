package com.monkey.context.temp_post.infra;

import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostAuthor;
import com.monkey.context.temp_post.domain.TempPostId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempPostRepository extends JpaRepository<TempPost, TempPostId> {
    List<TempPost> findAllByAuthor(TempPostAuthor author, Pageable pageable);
}
