package com.monkey.context.member.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.member.infra.client.kakao.KakaoProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoTokenRequestDto {
    @JsonProperty(value = "grant_type", required = true)
    private String grantType = "authorization_code";

    @JsonProperty(value = "client_id", required = true)
    private String clientId;

    @JsonProperty(value = "redirect_uri", required = true)
    private String redirectUri;

    @JsonProperty(value = "code", required = true)
    private String code;

    @JsonProperty(value = "client_secret")
    private String clientSecret;

    public KakaoTokenRequestDto(String code, KakaoProperties properties) {
        this.clientId = properties.getClientId();
        this.redirectUri = properties.getRedirectUri();
        this.code = code;
        this.clientSecret = properties.getClientSecret();
    }
}
