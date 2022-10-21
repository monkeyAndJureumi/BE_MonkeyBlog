package com.monkey.context.token.dto;

import com.monkey.context.member.enums.OauthType;
import com.monkey.context.token.annotation.JwtTokenConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenAccessRequestDto {

    private OauthType oauthType;

    @JwtTokenConstraint(message = "invalid token type")
    private String accessToken;
}
