package com.monkey.context.temp_post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.temp_post.domain.TempPost;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPostIndexListDto {
    @JsonProperty("data")
    private List<TempPostIndex> data;

    public TempPostIndexListDto(List<TempPost> tempPostList) {
        this.data = tempPostList.stream().map(TempPostIndex::new).collect(Collectors.toList());
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
