package com.monkey.aggregate.user.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.annotation.JwtTokenValid;
import com.monkey.aggregate.user.enums.GrantType;
import com.monkey.aggregate.user.validation.groups.OAuthValidateGroups;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RefreshRequestDto {
    @JsonProperty("grant_type")
    @NotNull(groups = OAuthValidateGroups.GrantType.class, message = "invalid grant_type")
    private GrantType grantType;

    @JsonProperty("access_token")
    @JwtTokenValid(groups = OAuthValidateGroups.NotNullAccessToken.class, message = "invalid access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    @JwtTokenValid(groups = OAuthValidateGroups.NotNullRefreshToken.class, message = "invalid refresh_token")
    private String refreshToken;
}
