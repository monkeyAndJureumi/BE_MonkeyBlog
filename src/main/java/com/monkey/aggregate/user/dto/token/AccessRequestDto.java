package com.monkey.aggregate.user.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.annotation.JwtTokenValid;
import com.monkey.aggregate.user.enums.GrantType;
import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.aggregate.user.validation.groups.OAuthValidateGroups;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class AccessRequestDto {
    @JsonProperty("grant_type")
    @NotNull(groups = {OAuthValidateGroups.GrantType.class}, message = "invalid grant_type")
    private GrantType grantType;

    @JsonProperty("social_type")
    @NotNull(groups = {OAuthValidateGroups.SocialType.class}, message = "invalid social_type")
    private SocialType socialType;

    @JsonProperty("access_token")
    @JwtTokenValid(groups = {OAuthValidateGroups.NotNullAccessToken.class}, message = "invalid access_token")
    private String accessToken;
}
