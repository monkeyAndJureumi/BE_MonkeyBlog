package com.monkey.aggregate.temporary.dto;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TemporaryPostUpdateDto extends UserSessionDto {
    private String postId;
    private String content;
}
