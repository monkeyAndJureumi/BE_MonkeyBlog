package com.monkey.aggregate.notice.view;

import com.monkey.aggregate.notice.domain.Notice;
import com.monkey.aggregate.notice.dto.NoticeDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeRes {
    private List<NoticeDto> notices;
    private int currentPage;
    private long totalElements;
    private int totalPages;

    public NoticeRes(Page<Notice> noticePage) {
        this.notices = noticePage.getContent().stream()
                .map(notice -> new NoticeDto(notice.getTitle(), notice.getContent(), notice.getCreatedAt(), notice.getModifiedAt()))
                .collect(Collectors.toList());
        this.currentPage = noticePage.getNumber() + 1;
        this.totalElements = noticePage.getTotalElements();
        this.totalPages = noticePage.getTotalPages();
    }
}
