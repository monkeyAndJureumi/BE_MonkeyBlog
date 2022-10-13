package com.monkey.context.token.dto;

import com.monkey.context.user.enums.OauthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenAccessRequestDto {
    private OauthType oauthType;
    private String accessToken;
}
