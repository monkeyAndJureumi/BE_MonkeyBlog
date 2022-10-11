package com.monkey.aggregate.token.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.aggregate.token.dto.TokenAccessRequestDto;
import com.monkey.aggregate.token.dto.TokenPostRequestDto;
import com.monkey.aggregate.token.dto.TokenRefreshRequestDto;
import com.monkey.aggregate.token.dto.TokenResponseDto;
import com.monkey.aggregate.token.service.TokenService;
import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public enum TokenGrantType {
    ACCESS_TOKEN("access_token", (service, dto) -> service.provideToken(new TokenAccessRequestDto(dto.getOauthType(), dto.getAccessToken()))),
    REFRESH_TOKEN("refresh_token", ((service, dto) -> service.refreshToken(new TokenRefreshRequestDto(dto.getRefreshToken()))));

    private final String description;

    private final BiFunction<TokenService, TokenPostRequestDto, TokenResponseDto> tokenFunc;

    TokenGrantType(String description, BiFunction<TokenService, TokenPostRequestDto, TokenResponseDto> tokenFunc) {
        this.description = description;
        this.tokenFunc = tokenFunc;
    }

    @JsonCreator
    public static TokenGrantType create(String value) {
        for (TokenGrantType type : TokenGrantType.values()) {
            if (type.name().equalsIgnoreCase(value))
                return type;
        }

        return null;
    }
}
