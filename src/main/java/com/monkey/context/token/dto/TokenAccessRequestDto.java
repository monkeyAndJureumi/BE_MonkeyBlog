package com.monkey.context.token.dto;

import com.monkey.context.member.enums.OAuthType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenAccessRequestDto {
    private OAuthType oauthType;
    private String authorizationCode;
}
