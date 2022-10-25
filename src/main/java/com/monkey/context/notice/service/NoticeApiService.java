package com.monkey.context.notice.service;

import com.monkey.context.notice.domain.Notice;
import com.monkey.context.notice.infra.repository.NoticeRepository;
import com.monkey.context.notice.dto.NoticeIndexResponseDto;
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

    public NoticeIndexResponseDto getNoticePageOrderByCreatedAtDesc(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        Page<Notice> noticePage = noticeRepository.findAll(pageRequest);

        return new NoticeIndexResponseDto(noticePage);
    }

    public NoticeIndexResponseDto getNoticePageOrderByModifiedAtDesc(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("modifiedAt").descending());
        Page<Notice> noticePage = noticeRepository.findAll(pageRequest);

        return new NoticeIndexResponseDto(noticePage);
    }
}
