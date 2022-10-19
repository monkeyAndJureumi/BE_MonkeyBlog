package com.monkey.context.token.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.context.token.dto.TokenAccessRequestDto;
import com.monkey.context.token.dto.TokenPostRequestDto;
import com.monkey.context.token.dto.TokenRefreshRequestDto;
import com.monkey.context.token.dto.TokenResponseDto;
import com.monkey.context.token.service.TokenService;
import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public enum TokenGrantType {
    ACCESS_TOKEN((service, dto) -> service.provideToken(new TokenAccessRequestDto(dto.getOauthType(), dto.getAccessToken()))),
    REFRESH_TOKEN(((service, dto) -> service.refreshToken(new TokenRefreshRequestDto(dto.getRefreshToken()))));


    private final BiFunction<TokenService, TokenPostRequestDto, TokenResponseDto> tokenFunc;

    TokenGrantType(BiFunction<TokenService, TokenPostRequestDto, TokenResponseDto> tokenFunc) {
        this.tokenFunc = tokenFunc;
    }

    @JsonCreator
    public static TokenGrantType create(String value) {
        for (TokenGrantType type : TokenGrantType.values()) {
            if (type.name().equals(value))
                return type;
        }

        throw new IllegalArgumentException("invalid grant_type");
    }
}
