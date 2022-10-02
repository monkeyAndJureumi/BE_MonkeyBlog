package com.monkey.aggregate.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.token.annotation.GrantTypeConstraint;
import com.monkey.aggregate.token.annotation.JwtTokenConstraint;
import com.monkey.aggregate.token.annotation.TokenRequestConstraint;
import com.monkey.aggregate.token.enums.GrantType;
import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.aggregate.token.marker.AccessType;
import com.monkey.aggregate.token.marker.RefreshType;
import com.monkey.aggregate.token.validation.groups.TokenRequestGroups;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
@TokenRequestConstraint
public class TokenRequestDto {
    @JsonProperty("grant_type")
    @NotNull(groups = {TokenRequestGroups.GrantType.class}, message = "not null grant_type")
    @GrantTypeConstraint(groups = {AccessType.class, RefreshType.class})
    private GrantType grant_type;

    @JsonProperty("social_type")
    @NotNull(groups = {TokenRequestGroups.SocialType.class}, message = "not null social_type")
    private SocialType social_type;

    @JsonProperty("access_token")
    @NotNull(groups = {TokenRequestGroups.AccessToken.class}, message = "not null access_token")
    @JwtTokenConstraint(groups = AccessType.class)
    private String access_token;

    @JsonProperty("refresh_token")
    @NotNull(groups = {TokenRequestGroups.RefreshToken.class}, message = "not null refresh_token")
    @JwtTokenConstraint(groups = RefreshType.class)
    private String refresh_token;
}
