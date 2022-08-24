package com.monkey.domain.comment.controller;

import com.monkey.domain.comment.entity.CommentId;
import com.monkey.domain.comment.service.CommentApiService;
import com.monkey.domain.comment.service.CommentService;
import com.monkey.domain.comment.view.CommentDeleteReq;
import com.monkey.domain.comment.view.CommentSaveReq;
import com.monkey.domain.comment.view.ReplyCommentsRes;
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
    public ResponseEntity<ReplyCommentsRes> getReplyComments(@RequestParam("commentId") Long commentId) {
        ReplyCommentsRes res = commentApiService.getReplyComments(new CommentId(commentId));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> saveComment(@RequestBody CommentSaveReq req) {
        commentService.saveComment(req);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteComment(@RequestBody CommentDeleteReq req) {
        commentService.deleteComment(req);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
