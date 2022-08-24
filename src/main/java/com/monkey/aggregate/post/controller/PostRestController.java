package com.monkey.aggregate.post.controller;

import com.monkey.aggregate.post.entity.PostId;
import com.monkey.aggregate.post.service.PostApiService;
import com.monkey.aggregate.post.service.PostService;
import com.monkey.aggregate.post.view.PostDeleteReq;
import com.monkey.aggregate.post.view.PostRes;
import com.monkey.aggregate.post.view.PostSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostRestController {
    private final PostService postService;
    private final PostApiService postApiService;

    @GetMapping
    public ResponseEntity<PostRes> get(@RequestParam("id") Long id) {
        PostRes res = postApiService.getPost(new PostId(id));
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody PostSaveReq req) {
        postService.savePost(req);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody PostDeleteReq req) {
        postService.deletePost(req);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
