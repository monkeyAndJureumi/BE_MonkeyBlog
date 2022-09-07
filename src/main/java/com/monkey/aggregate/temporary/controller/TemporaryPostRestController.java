package com.monkey.aggregate.temporary.controller;

import com.monkey.aggregate.temporary.dto.TemporaryPostIndexResponseDto;
import com.monkey.aggregate.temporary.dto.TemporaryPostResponseDto;
import com.monkey.aggregate.temporary.domain.TemporaryPostId;
import com.monkey.aggregate.temporary.service.TemporaryPostApiService;
import com.monkey.aggregate.temporary.service.TemporaryPostService;
import com.monkey.aggregate.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/temporary_post")
public class TemporaryPostRestController {
    private final TemporaryPostService temporaryPostService;
    private final TemporaryPostApiService temporaryPostApiService;

    @GetMapping
    public ResponseEntity<TemporaryPostResponseDto> getPost(UserId userId,
                                                            @RequestParam("post_id") TemporaryPostId postId) {
        TemporaryPostResponseDto response = temporaryPostService.getPost(postId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<TemporaryPostIndexResponseDto> getIndexList(UserId userId) {
        TemporaryPostIndexResponseDto response = temporaryPostApiService.selectIndexList(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deletePost(UserId userId,
                                                 @RequestParam("post_id") TemporaryPostId postId) {
        temporaryPostService.deletePost(postId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
