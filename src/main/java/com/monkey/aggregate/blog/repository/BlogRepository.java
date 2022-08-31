package com.monkey.aggregate.blog.repository;

import com.monkey.aggregate.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, String> {
}
