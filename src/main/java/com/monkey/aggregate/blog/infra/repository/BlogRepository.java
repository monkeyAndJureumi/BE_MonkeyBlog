package com.monkey.aggregate.blog.infra.repository;

import com.monkey.aggregate.blog.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, String> {
}
