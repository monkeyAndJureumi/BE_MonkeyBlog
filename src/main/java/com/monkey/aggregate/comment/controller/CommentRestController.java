package com.monkey.aggregate.comment.controller;

import com.monkey.aggregate.comment.dto.CommentResponseDto;
import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.service.CommentApiService;
import com.monkey.aggregate.comment.service.CommentService;
import com.monkey.aggregate.comment.dto.CommentSaveDto;
import com.monkey.aggregate.comment.dto.CommentUpdateDto;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aop.annotation.NonRequiredParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "게시글의 댓글 목록을 리턴")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId", value = "사용자 인덱스번호(Authorization)"),
//            @ApiImplicitParam(name = "postId", value = "게시글 인덱스번호", required = true)
//    })
    @GetMapping
    public ResponseEntity<CommentResponseDto> getCommentsByPost(
            @NonRequiredParam @ApiIgnore final UserId userId,
            @RequestParam("post_id") @ApiParam(value = "게시글 인덱스번호") final Long postId) {
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
        commentService.save(dto);
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
