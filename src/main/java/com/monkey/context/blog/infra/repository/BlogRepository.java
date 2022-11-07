package com.monkey.context.blog.infra.repository;

import com.monkey.context.blog.domain.Blog;
import com.monkey.context.blog.domain.BlogId;
import com.monkey.context.blog.infra.repository.custom.BlogCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, BlogId>, BlogCustomRepository {


}
