package com.monkey.context.temp_post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPostSaveDto extends UserSessionDto {
    @JsonProperty(value = "content")
    private String content;

    TempPostSaveDto(String content) {
        this.content = content;
    }
}
