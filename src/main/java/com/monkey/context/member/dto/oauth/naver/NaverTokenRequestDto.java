package com.monkey.context.member.dto.oauth.naver;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Deprecated
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaverTokenRequestDto {
    @JsonProperty(value = "grant_type", required = true)
    private String grantType;

    @JsonProperty(value = "client_id", required = true)
    private String clientId;

    @JsonProperty(value = "client_secret", required = true)
    private String clientSecret;

    @JsonProperty("code")
    private String code;

    @JsonProperty("state")
    private String state;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("service_provider")
    private String serviceProvider;

    @Builder
    private NaverTokenRequestDto(String grantType, String clientId, String clientSecret, String code, String state, String refreshToken, String accessToken, String serviceProvider) {
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
        this.state = state;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.serviceProvider = serviceProvider;
    }

    public static NaverTokenRequestDto createIssuedDto(String grantType, String clientId, String clientSecret, String code, String state) {
        return NaverTokenRequestDto.builder().grantType(grantType).clientId(clientId).clientSecret(clientSecret).code(code).state(state).build();
    }

    public static NaverTokenRequestDto createRefreshDto(String grantType, String clientId, String clientSecret, String refreshToken) {
        return NaverTokenRequestDto.builder().grantType(grantType).clientId(clientId).clientSecret(clientSecret).refreshToken(refreshToken).build();
    }

    public static NaverTokenRequestDto createDeleteDto(String grantType, String clientId, String clientSecret, String accessToken) {
        return NaverTokenRequestDto.builder().grantType(grantType).clientId(clientId).clientSecret(clientSecret).accessToken(accessToken).serviceProvider("NAVER").build();
    }
}
