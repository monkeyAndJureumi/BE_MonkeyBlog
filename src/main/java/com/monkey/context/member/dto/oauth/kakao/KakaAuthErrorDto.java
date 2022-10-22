package com.monkey.context.member.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaAuthErrorDto {
    private HttpStatus httpStatus;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String description;

    @JsonProperty("error_code")
    private String code;
}
