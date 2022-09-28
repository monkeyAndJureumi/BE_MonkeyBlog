package com.monkey.aggregate.post_temp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostTempUpdateDto extends UserSessionDto {
    @JsonProperty(value = "post_id")
    private String postTempId;

    @JsonProperty(value = "content")
    private String content;
}
