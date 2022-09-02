package com.monkey.aggregate.temporary.view;

import com.monkey.dto.UserSessionDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TemporaryPostSaveReq extends UserSessionDto {
    private String content;
}
