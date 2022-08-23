package com.monkey.aggregate.notice.service;

import com.monkey.aggregate.notice.domain.Notice;
import com.monkey.aggregate.notice.repository.NoticeRepository;
import com.monkey.aggregate.notice.view.NoticeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeApiService {
    private final NoticeRepository noticeRepository;

    public NoticeRes getNoticePage(Pageable pageable) {
        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        return new NoticeRes(noticePage);
    }
}
