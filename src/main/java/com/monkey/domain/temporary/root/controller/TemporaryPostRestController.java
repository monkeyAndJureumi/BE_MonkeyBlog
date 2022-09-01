package com.monkey.domain.temporary.root.controller;

import com.monkey.domain.temporary.root.entity.TemporaryPostId;
import com.monkey.domain.temporary.root.service.TemporaryPostService;
import com.monkey.domain.temporary.root.view.TemporaryPostRes;
import com.monkey.domain.user.root.entity.UserId;
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
