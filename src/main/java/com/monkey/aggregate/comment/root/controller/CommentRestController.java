package com.monkey.aggregate.comment.root.controller;

import com.monkey.aggregate.comment.root.dto.CommentResponseDto;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.comment.root.service.CommentApiService;
import com.monkey.aggregate.comment.root.service.CommentService;
import com.monkey.aggregate.comment.root.dto.CommentSaveDto;
import com.monkey.aggregate.comment.root.dto.CommentUpdateDto;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.aop.annotation.NonRequiredParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentRestController {
    private final CommentService commentService;
    private final CommentApiService commentApiService;

    @GetMapping
    public ResponseEntity<CommentResponseDto> getCommentsByPost(@NonRequiredParam final UserId userId, @RequestParam("post_id") final Long postId) {
        CommentResponseDto responseDto = commentApiService.selectByPostId(userId, new PostId(postId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{comment_id}")
    public ResponseEntity<CommentResponseDto> getReplyComments(@NonRequiredParam final UserId userId, @PathVariable("comment_id") final Long commentId) {
        CommentResponseDto responseDto = commentApiService.selectByCommentId(userId, new CommentId(commentId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveComment(@RequestBody final CommentSaveDto dto) {
        commentService.saveComment(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> patchComment(@RequestBody final CommentUpdateDto dto) {
        commentService.modifyComment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{comment_id}")
    public ResponseEntity<HttpStatus> deleteComment(final UserId userId, @PathVariable("comment_id") final Long commentId) {
        commentService.deleteComment(userId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
