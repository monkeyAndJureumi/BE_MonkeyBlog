package com.monkey.context.temp_post.controller;

import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostResponseDto;
import com.monkey.context.temp_post.dto.TempPostSaveDto;
import com.monkey.context.temp_post.dto.TempPostUpdateDto;
import com.monkey.context.temp_post.service.TempPostService;
import com.monkey.context.member.domain.MemberId;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/post/temp")
@RequiredArgsConstructor
public class TempPostRestController {
    private final TempPostService tempPostService;

    @ApiOperation(value = "임시 게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<TempPostResponseDto> get(MemberId memberId, @PathVariable("id") String id) {
        return new ResponseEntity<>(tempPostService.select(memberId, new TempPostId(id)), HttpStatus.OK);
    }

    @ApiOperation(value = "임시 게시글 생성")
    @PostMapping
    public ResponseEntity<TempPostId> save(@RequestBody TempPostSaveDto dto) {
        return new ResponseEntity<>(tempPostService.save(dto), HttpStatus.OK);
    }

    @ApiOperation(value = "임시 게시글 수정")
    @PatchMapping
    public ResponseEntity<HttpStatus> update(@RequestBody TempPostUpdateDto dto) {
        tempPostService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "임시 게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@ApiIgnore MemberId memberId, @PathVariable("id") String id) {
        tempPostService.delete(memberId, new TempPostId(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
