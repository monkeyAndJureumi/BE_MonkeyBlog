package com.monkey.domain.blog.root.service;

import com.monkey.domain.blog.root.dto.BlogSaveDto;
import com.monkey.domain.blog.root.entity.Blog;
import com.monkey.domain.blog.root.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogService {
    private final BlogRepository blogRepository;

    public void save(final BlogSaveDto dto) {
        Blog blog = Blog.create(generateId(dto), dto.getUserId(), dto.getBlogName());
        blogRepository.save(blog);
    }

    private String generateId(final BlogSaveDto dto) {
        long now = System.currentTimeMillis();
        return now + "-" + dto.getUuid();
    }
}
