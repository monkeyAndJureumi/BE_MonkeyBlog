package com.monkey.context.blog.service;

import com.monkey.context.blog.domain.Blog;
import com.monkey.context.blog.domain.BlogId;
import com.monkey.context.blog.dto.BlogCreateDto;
import com.monkey.context.blog.dto.BlogIndexResponseDto;
import com.monkey.context.blog.dto.BlogUpdateDto;
import com.monkey.context.blog.infra.repository.BlogRepository;
import com.monkey.context.blog.infra.repository.query.BlogIndex;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.grant.service.GrantService;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogService {
    private final BlogRepository blogRepository;
    private final GrantService permissionService;

    public BlogIndexResponseDto getIndexList(MemberId memberId) {
        List<BlogIndex> result = blogRepository.findAllIndexByMemberId(memberId);
        return new BlogIndexResponseDto(result);
    }

    @Transactional
    public BlogId create(BlogCreateDto dto) {
        Blog blog = new Blog(dto);
        return blogRepository.save(blog).getBlogId();
    }

    @Transactional
    public void delete(MemberId memberId, BlogId blogId) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new MonkeyException());
        permissionService.authorize(memberId, blog.getMemberId());
        blogRepository.delete(blog);
    }

    @Transactional
    public void modify(BlogId blogId, BlogUpdateDto dto) {
        Blog blog = blogRepository.findById(blogId).orElseThrow();
        permissionService.authorize(dto.getMemberId(), blog.getMemberId());
        blog.update(dto);
    }
}
