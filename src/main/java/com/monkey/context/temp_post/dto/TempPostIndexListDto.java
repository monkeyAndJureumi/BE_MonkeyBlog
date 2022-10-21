package com.monkey.context.temp_post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.temp_post.domain.TempPost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPostIndexListDto {
    private long totalElements;
    private int totalPages;

    @JsonProperty("data")
    private List<TempPostIndex> data;

    public TempPostIndexListDto(Page<TempPost> result) {
        this.totalElements = result.getTotalElements();
        this.totalPages = result.getTotalPages();
        this.data = result.get().map(TempPostIndex::new).collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class TempPostIndex {
        private String title;

        public TempPostIndex(TempPost tempPost) {
            this.title = tempPost.getTitle();
        }
    }
}
