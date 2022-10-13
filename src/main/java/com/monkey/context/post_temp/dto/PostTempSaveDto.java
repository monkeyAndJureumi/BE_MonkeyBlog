package com.monkey.context.post_temp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostTempSaveDto extends UserSessionDto {
    @JsonProperty(value = "content")
    private String content;
}
