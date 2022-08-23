package com.monkey.aggregate.notice.controller;

import com.monkey.aggregate.notice.service.NoticeApiService;
import com.monkey.aggregate.notice.view.NoticeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        NoticeRes response = noticeApiService.getNoticePage(pageRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
