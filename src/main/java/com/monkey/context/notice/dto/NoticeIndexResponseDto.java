package com.monkey.context.notice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.notice.domain.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NoticeIndexResponseDto {
    private int currentPage;
    private long totalElements;
    private int totalPages;
    private List<Index> data;

    public NoticeIndexResponseDto(Page<Notice> noticePage) {
        this.data = noticePage.getContent().stream()
                .map(Index::new)
                .collect(Collectors.toList());
        this.currentPage = noticePage.getNumber() + 1;
        this.totalElements = noticePage.getTotalElements();
        this.totalPages = noticePage.getTotalPages();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Index {
        @JsonProperty("title")
        private String title;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("modified_at")
        private LocalDateTime modifiedAt;

        public Index(Notice notice) {
            this.title = notice.getTitle();
            this.createdAt = notice.getCreatedAt();
            this.modifiedAt = notice.getModifiedAt();
        }
    }
}
