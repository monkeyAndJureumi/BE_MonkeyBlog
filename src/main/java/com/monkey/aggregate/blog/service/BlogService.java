package com.monkey.aggregate.blog.service;

import com.monkey.aggregate.blog.dto.BlogSaveDto;
import com.monkey.aggregate.blog.entity.Blog;
import com.monkey.aggregate.blog.repository.BlogRepository;
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
