package com.monkey.context.post_temp.controller;

import com.monkey.context.post_temp.dto.PostTempResponseDto;
import com.monkey.context.post_temp.dto.PostTempSaveDto;
import com.monkey.context.post_temp.dto.PostTempUpdateDto;
import com.monkey.context.post_temp.service.PostTempService;
import com.monkey.context.user.domain.UserId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
    private final PostTempService postTempService;

    @ApiOperation(value = "게시글 조회")
    @GetMapping("/{id}")
    public ResponseEntity<PostTempResponseDto> get(UserId userId, @PathVariable("id") String postId) {
        return new ResponseEntity<>(postTempService.select(userId, postId), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 생성")
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody PostTempSaveDto dto) {
        postTempService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiModelProperty(value = "게시글 수정")
    @PatchMapping
    public ResponseEntity<HttpStatus> update(@RequestBody PostTempUpdateDto dto) {
        postTempService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiModelProperty(value = "게시글 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@ApiIgnore UserId userId, @PathVariable("id") String id) {
        postTempService.delete(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
