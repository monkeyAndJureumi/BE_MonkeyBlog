package com.monkey.aggregate.post_temp.controller;

import com.monkey.aggregate.post_temp.dto.PostTempSaveDto;
import com.monkey.aggregate.post_temp.dto.PostTempUpdateDto;
import com.monkey.aggregate.post_temp.service.PostTempService;
import com.monkey.aggregate.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
    private final PostTempService postTempService;

    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody PostTempSaveDto dto) {
        postTempService.save(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> update(@RequestBody PostTempUpdateDto dto) {
        postTempService.update(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> save(UserId userId,  @RequestParam("post_id") String id) {
        postTempService.delete(userId, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
