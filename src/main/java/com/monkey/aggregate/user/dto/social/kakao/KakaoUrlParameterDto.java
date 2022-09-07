package com.monkey.aggregate.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoUrlParameterDto {
    @JsonProperty(value = "grant_type", required = true)
    private String grantType;

    @JsonProperty(value = "client_id", required = true)
    private String clientId;

    @JsonProperty(value = "redirect_uri", required = true)
    private String redirectUri;

    @JsonProperty(value = "code", required = true)
    private String code;

    @JsonProperty(value = "client_secret")
    private String clientSecret;
}
