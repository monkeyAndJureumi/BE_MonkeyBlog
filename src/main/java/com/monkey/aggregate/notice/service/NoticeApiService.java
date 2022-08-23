package com.monkey.aggregate.notice.service;

import com.monkey.aggregate.notice.domain.Notice;
import com.monkey.aggregate.notice.repository.NoticeRepository;
import com.monkey.aggregate.notice.view.NoticeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeApiService {
    private final NoticeRepository noticeRepository;

    public NoticeRes getNoticePageOrderByCreatedAtDesc(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Notice> noticePage = noticeRepository.findAll(pageRequest);

        return new NoticeRes(noticePage);
    }

    public NoticeRes getNoticePageOrderByModifiedAtDesc(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("modifiedAt").descending());
        Page<Notice> noticePage = noticeRepository.findAll(pageRequest);

        return new NoticeRes(noticePage);
    }
}
