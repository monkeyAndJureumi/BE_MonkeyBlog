package com.monkey.aggregate.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.token.annotation.BearerTokenConstraint;
import com.monkey.aggregate.token.annotation.TokenRequestConstraint;
import com.monkey.aggregate.token.enums.GrantType;
import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.aggregate.token.validation.groups.TokenRequestGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@TokenRequestConstraint(message = "invalid grant_type")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRequestDto {
    @JsonProperty("grant_type")
    @ApiModelProperty(value = "요청 타입, 대소문자 구분 X")
    private GrantType grantType;

    @JsonProperty("social_type")
    @NotNull(groups = {TokenRequestGroups.SocialType.class}, message = "invalid social_type")
    @ApiModelProperty(value = "요청타입이 access_token인 경우에만 설정, 대소문자 구분 X")
    private SocialType socialType;

    @JsonProperty("access_token")
    @BearerTokenConstraint(groups = TokenRequestGroups.AccessToken.class, message = "invalid access_token")
    @ApiModelProperty(value = "소셜로그인 진행 후 받은 액세스 토큰, grant_type 이 access_token인 경우에 설정")
    private String accessToken;

    @JsonProperty("refresh_token")
    @BearerTokenConstraint(groups = TokenRequestGroups.RefreshToken.class, message = "invalid refresh_token")
    @ApiModelProperty(value = "토큰을 재발급 받기 위한 리프레시 토큰, grant_type 이 refresh_token인 경우에 설정")
    private String refreshToken;
}
