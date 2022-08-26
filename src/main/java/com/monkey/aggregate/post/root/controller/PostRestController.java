package com.monkey.aggregate.post.root.controller;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.post.root.service.PostService;
import com.monkey.aggregate.post.root.dto.PostResponseDto;
import com.monkey.aggregate.post.root.dto.PostSaveDto;
import com.monkey.aggregate.user.root.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> select(@PathVariable("post_id") Long id) {
        return new ResponseEntity<>(postRepository.selectByPostId(new PostId(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody PostSaveDto req) {
        postService.savePost(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(UserId userId, @RequestParam("id") Long postId) {
        postService.deletePost(userId, new PostId(postId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
