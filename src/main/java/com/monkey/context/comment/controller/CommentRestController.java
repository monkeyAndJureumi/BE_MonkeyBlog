package com.monkey.context.comment.controller;

import com.monkey.context.comment.dto.CommentResponseDto;
import com.monkey.context.comment.domain.CommentId;
import com.monkey.context.comment.service.CommentApiService;
import com.monkey.context.comment.service.CommentService;
import com.monkey.context.comment.dto.CommentSaveDto;
import com.monkey.context.comment.dto.CommentUpdateDto;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.member.domain.MemberId;
import com.monkey.aop.annotation.NonRequiredParam;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentRestController {
    private final CommentService commentService;
    private final CommentApiService commentApiService;

    @Operation(description = "게시글의 댓글 목록 리턴")
    @GetMapping
    public ResponseEntity<CommentResponseDto> getCommentsByPost(
            @NonRequiredParam @ApiIgnore final MemberId memberId,
            @RequestParam("post_id") @ApiParam(value = "게시글 인덱스번호") final Long postId) {
        CommentResponseDto responseDto = commentApiService.selectByPostId(memberId, new PostId(postId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(description = "대댓글 목록 리턴")
    @GetMapping("/{comment_id}")
    public ResponseEntity<CommentResponseDto> getReplyComments(@NonRequiredParam @ApiIgnore final MemberId memberId, @PathVariable("comment_id") final Long commentId) {
        CommentResponseDto responseDto = commentApiService.selectByCommentId(memberId, new CommentId(commentId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(description = "댓글 저장")
    @PostMapping
    public ResponseEntity<HttpStatus> saveComment(@RequestBody final CommentSaveDto dto) {
        commentService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(description = "댓글 수정")
    @PatchMapping
    public ResponseEntity<HttpStatus> patchComment(@RequestBody final CommentUpdateDto dto) {
        commentService.modifyComment(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(description = "댓글 삭제")
    @DeleteMapping("/{comment_id}")
    public ResponseEntity<HttpStatus> deleteComment(final MemberId memberId, @PathVariable("comment_id") final Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
