package com.monkey.aggregate.post_temp.dto;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostTempSaveDto extends UserSessionDto {
    private String content;
}
