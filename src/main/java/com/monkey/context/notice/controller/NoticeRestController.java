package com.monkey.context.notice.controller;

import com.monkey.context.notice.service.NoticeApiService;
import com.monkey.context.notice.dto.NoticeIndexResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoticeRestController {
    private final NoticeApiService noticeApiService;

    @GetMapping
    public ResponseEntity<NoticeIndexResponseDto> findByPageable(@RequestParam("page") int page,
                                                                 @RequestParam("size") int size) {
        NoticeIndexResponseDto response = noticeApiService.getNoticePageOrderByCreatedAtDesc(page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
