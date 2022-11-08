package com.monkey.context.post.controller;

import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostResponseDto;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostUpdateDto;
import com.monkey.context.post.infra.repository.PostRepository;
import com.monkey.context.post.service.PostService;
import com.monkey.context.member.domain.MemberId;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostRestController {
    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("/{post_id}")
    public ResponseEntity<PostResponseDto> select(@PathVariable("post_id") Long id) {
        return new ResponseEntity<>(postRepository.selectByPostId(new PostId(id))
                .orElseThrow(() -> new MonkeyException(CommonErrorCode.E400)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostId> save(@ApiIgnore MemberId memberId, @RequestBody PostSaveDto dto) {
        return new ResponseEntity<>(postService.save(memberId, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<HttpStatus> delete(MemberId memberId, @PathVariable("post_id") Long postId) {
        postService.delete(memberId, new PostId(postId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{post_id}")
    public ResponseEntity<HttpStatus> update(MemberId memberId, @PathVariable("post_id") Long postId, PostUpdateDto dto) {
        postService.modify(memberId, postId, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
