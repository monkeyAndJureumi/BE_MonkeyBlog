package com.monkey.context.post.controller;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/like")
public class PostLikeRestController {
    private final PostLikeService postLikeService;

    @PostMapping("/{post_id}")
    public ResponseEntity<HttpStatus> addLike(MemberId memberId, @PathVariable("post_id") Long id) {
        postLikeService.addLike(memberId, new PostId(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<HttpStatus> deleteLike(MemberId memberId, @PathVariable("post_id") Long id) {
        postLikeService.deleteLike(memberId, new PostId(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
