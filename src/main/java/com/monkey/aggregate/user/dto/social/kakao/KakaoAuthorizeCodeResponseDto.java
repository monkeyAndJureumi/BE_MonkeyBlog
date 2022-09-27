package com.monkey.aggregate.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.dto.social.AuthorizeCodeResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Deprecated
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoAuthorizeCodeResponseDto extends AuthorizeCodeResponseDto {
    @JsonProperty("code")
    private String code;

    @JsonProperty("state")
    private String state;

    @JsonProperty("error")
    private String error;

    @JsonProperty("error_description")
    private String errorDescription;
}
