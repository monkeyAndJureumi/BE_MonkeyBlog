package com.monkey.context.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.context.token.annotation.TokenPostRequestConstraint;
import com.monkey.context.token.enums.TokenGrantType;
import com.monkey.context.token.validator.groups.TokenRequestGroups;
import com.monkey.context.member.enums.OauthType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@TokenPostRequestConstraint(message = "invalid grant_type")
public class TokenPostRequestDto {
    @JsonProperty("grant_type")
    @Schema(description = "요청 타입(소문자로 입력)")
    private TokenGrantType grantType;

    @JsonProperty("oauth_type")
    @NotNull(groups = TokenRequestGroups.OAuthType.class, message = "oauth_type is not null")
    @Schema(description = "액세스 토큰을 발급해준 서비스 이름(소문자로 입력)")
    private OauthType oauthType;

    @JsonProperty("access_token")
    @NotNull(groups = TokenRequestGroups.AccessToken.class, message = "access_token is not null")
    @Schema(description = "소셜로그인 진행 후 받은 액세스 토큰")
    private String accessToken;

    @JsonProperty("refresh_token")
    @NotNull(groups = TokenRequestGroups.RefreshToken.class, message = "refresh_token is not null")
    @Schema(description = "어플리케이션으로부터 발급받은 리프레시 토큰")
    private String refreshToken;
}
