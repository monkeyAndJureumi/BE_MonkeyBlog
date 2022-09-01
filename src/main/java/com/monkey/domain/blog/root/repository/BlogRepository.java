package com.monkey.domain.blog.root.repository;

import com.monkey.domain.blog.root.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, String> {
}
