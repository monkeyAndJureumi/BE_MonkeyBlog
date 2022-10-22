package com.monkey.context.member.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoApiErrorDto {
    @JsonProperty("msg")
    private String msg;

    @JsonProperty("code")
    private String code;
}
