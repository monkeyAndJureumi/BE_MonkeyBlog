package com.monkey.context.temp_post.controller;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostIndexListDto;
import com.monkey.context.temp_post.dto.TempPostResponseDto;
import com.monkey.context.temp_post.service.TempPostQueryService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/temp")
public class TempPostQueryRestController {
    private final TempPostQueryService tempPostQueryService;

    @ApiOperation(value = "임시 게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<TempPostResponseDto> get(@ApiIgnore MemberId memberId, @PathVariable("id") String id) {
        return new ResponseEntity<>(tempPostQueryService.getTempPost(memberId, new TempPostId(id)), HttpStatus.OK);
    }

    @GetMapping("/index")
    public ResponseEntity<TempPostIndexListDto> getIndexList(@ApiIgnore MemberId memberId, @RequestParam("page") int page, @RequestParam("size") int size) {
        return new ResponseEntity<>(tempPostQueryService.getIndexList(memberId, page, size), HttpStatus.OK);
    }
}
