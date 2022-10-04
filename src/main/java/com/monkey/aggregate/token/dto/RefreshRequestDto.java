package com.monkey.aggregate.token.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.token.annotation.BearerTokenConstraint;
import com.monkey.aggregate.token.enums.GrantType;
import com.monkey.aggregate.token.validation.groups.TokenRequestGroups;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshRequestDto {
    @JsonProperty("grant_type")
    @NotNull(groups = TokenRequestGroups.GrantType.class, message = "invalid grant_type")
    private GrantType grantType;

    @JsonProperty("access_token")
    @BearerTokenConstraint(groups = TokenRequestGroups.AccessToken.class, message = "invalid access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    @BearerTokenConstraint(groups = TokenRequestGroups.RefreshToken.class, message = "invalid refresh_token")
    private String refreshToken;
}
