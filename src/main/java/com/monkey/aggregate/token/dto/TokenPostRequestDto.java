package com.monkey.aggregate.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.token.annotation.JwtTokenConstraint;
import com.monkey.aggregate.token.annotation.TokenPostRequestConstraint;
import com.monkey.aggregate.token.enums.TokenGrantType;
import com.monkey.aggregate.token.validator.groups.TokenRequestGroups;
import com.monkey.aggregate.user.enums.OauthType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@TokenPostRequestConstraint(message = "invalid grant_type")
public class TokenPostRequestDto {
    @JsonProperty("grant_type")
    @ApiModelProperty(value = "요청 타입")
    private TokenGrantType grantType;

    @JsonProperty("oauth_type")
    @NotNull(groups = TokenRequestGroups.OAuthType.class, message = "invalid oauth_type")
    @ApiModelProperty(value = "액세스 토큰을 발급해준 리소스 서비스 이름")
    private OauthType oauthType;

    @JsonProperty("access_token")
    @JwtTokenConstraint(groups = TokenRequestGroups.AccessToken.class, message = "invalid access_token")
    @ApiModelProperty(value = "소셜로그인 진행 후 받은 액세스 토큰")
    private String accessToken;

    @JsonProperty("refresh_token")
    @JwtTokenConstraint(groups = TokenRequestGroups.RefreshToken.class, message = "invalid refresh_token")
    @ApiModelProperty(value = "발급받은 리프레시 토큰")
    private String refreshToken;
}
