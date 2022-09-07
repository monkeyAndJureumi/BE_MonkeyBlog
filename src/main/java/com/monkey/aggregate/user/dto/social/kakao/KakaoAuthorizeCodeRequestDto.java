package com.monkey.aggregate.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoAuthorizeCodeRequestDto {
    @JsonProperty(value = "client_id", required = true)
    private String clientId;

    @JsonProperty(value = "redirect_uri", required = true)
    private String redirectUri;

    @JsonProperty(value = "response_type", required = true)
    private String responseType;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("prompt")
    private String prompt;

    @JsonProperty("service_terms")
    private String serviceTerms;

    @JsonProperty("state")
    private String state;

    @JsonProperty("nonce")
    private String nonce;

    @Builder
    public KakaoAuthorizeCodeRequestDto(String clientId, String redirectUri, String scope, String prompt, String serviceTerms, String state, String nonce) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
        this.responseType = "code";
        this.scope = scope;
        this.prompt = prompt;
        this.serviceTerms = serviceTerms;
        this.state = state;
        this.nonce = nonce;
    }
}
