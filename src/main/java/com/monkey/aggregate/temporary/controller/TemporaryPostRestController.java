package com.monkey.aggregate.temporary.controller;

import com.monkey.aggregate.temporary.entity.TemporaryPostId;
import com.monkey.aggregate.temporary.service.TemporaryPostService;
import com.monkey.aggregate.temporary.view.TemporaryPostRes;
import com.monkey.aggregate.user.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temporary_post")
public class TemporaryPostRestController {
    private final TemporaryPostService temporaryPostService;

    @GetMapping
    public ResponseEntity<TemporaryPostRes> getPost(UserId userId,
                                                    @RequestParam("post_id") TemporaryPostId postId) {
        TemporaryPostRes response = temporaryPostService.getPost(postId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePost(UserId userId,
                                                 @RequestParam("post_id") TemporaryPostId postId) {
        temporaryPostService.deletePost(postId, userId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
