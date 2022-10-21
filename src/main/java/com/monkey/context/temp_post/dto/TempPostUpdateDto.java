package com.monkey.context.temp_post.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TempPostUpdateDto extends UserSessionDto {
    @JsonProperty(value = "temp_post_id")
    private String postTempId;

    @JsonProperty(value = "content")
    private String content;
}
