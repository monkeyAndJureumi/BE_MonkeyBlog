package com.monkey.context.member.dto.oauth.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.member.dto.oauth.OauthTokenResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Deprecated
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KakaoTokenResponseDto extends OauthTokenResponseDto {
    @JsonProperty(value = "token_type", required = true)
    private String tokenType;

    @JsonProperty(value = "access_token", required = true)
    private String accessToken;

    @JsonProperty(value = "id_token")
    private String idToken;

    @JsonProperty(value = "expires_in", required = true)
    private Integer accessTokenExpiresIn;

    @JsonProperty(value = "refresh_token", required = true)
    private String refreshToken;

    @JsonProperty(value = "refresh_token_expires_in", required = true)
    private Integer refreshTokenExpiresIn;

    @JsonProperty("scope")
    private String scope;

    public List<String> getScopeList() {
        List<String> result = new ArrayList<>();
        if (scope != null && (!scope.isEmpty() || !scope.isBlank())) {
            result = Arrays.asList(this.scope.split(", "));
        }
        return result;
    }
}
