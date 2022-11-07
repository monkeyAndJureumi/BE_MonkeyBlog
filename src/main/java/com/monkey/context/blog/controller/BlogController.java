package com.monkey.context.blog.controller;

import com.monkey.context.blog.domain.BlogId;
import com.monkey.context.blog.dto.BlogCreateDto;
import com.monkey.context.blog.dto.BlogIndexResponseDto;
import com.monkey.context.blog.dto.BlogUpdateDto;
import com.monkey.context.blog.service.BlogService;
import com.monkey.context.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/list")
    public ResponseEntity<BlogIndexResponseDto> getIndexList(MemberId memberId) {
        return new ResponseEntity<>(blogService.getIndexList(memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BlogId> createBlog(@RequestBody BlogCreateDto dto) {
        return new ResponseEntity<>(blogService.create(dto), HttpStatus.OK);
    }

    @PatchMapping("/{blog_id}")
    public ResponseEntity<HttpStatus> modifyBlog(@RequestBody BlogUpdateDto dto, @PathVariable("blog_id") String blogId) {
        blogService.modify(new BlogId(blogId), dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{blog_id}")
    public ResponseEntity<HttpStatus> deleteBlog(MemberId memberId, @PathVariable("blog_id") String blogId) {
        blogService.delete(memberId, new BlogId(blogId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
