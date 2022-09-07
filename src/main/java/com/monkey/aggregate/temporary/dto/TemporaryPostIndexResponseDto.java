package com.monkey.aggregate.temporary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemporaryPostIndexResponseDto {
    private int cnt;
    private List<Data> data;

    public TemporaryPostIndexResponseDto(List<TemporaryPostIndexDto> dtoList) {
        this.cnt = dtoList.size();
        this.data = dtoList.stream().map(Data::new).collect(Collectors.toList());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class Data {
        private String postId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;

        public Data(TemporaryPostIndexDto dto) {
            this.postId = dto.getPostId();
            this.createdAt = dto.getCreatedAt();
            this.modifiedAt = dto.getModifiedAt();
        }
    }
}
