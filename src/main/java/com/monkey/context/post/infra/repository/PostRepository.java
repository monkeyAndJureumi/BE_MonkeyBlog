package com.monkey.context.post.infra.repository;

import com.monkey.context.post.domain.Post;
import com.monkey.context.post.infra.repository.custom.PostCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
