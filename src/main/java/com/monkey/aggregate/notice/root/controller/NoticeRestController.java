package com.monkey.aggregate.notice.root.controller;

import com.monkey.aggregate.notice.root.service.NoticeApiService;
import com.monkey.aggregate.notice.root.view.NoticeRes;
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
    public ResponseEntity<NoticeRes> findByPageable(@RequestParam("page") int page,
                                            @RequestParam("size") int size) {
        NoticeRes response = noticeApiService.getNoticePageOrderByCreatedAtDesc(page, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}