package com.monkey.aggregate.token.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.dto.TokenResponseDto;
import com.monkey.aggregate.token.service.TokenService;
import lombok.Getter;

import java.util.function.BiFunction;

@Getter
public enum GrantType {
    ACCESS_TOKEN("access_token", TokenService::provideToken),
    REFRESH_TOKEN("refresh_token", TokenService::refreshToken);

    private final String description;

    private final BiFunction<TokenService, TokenRequestDto, TokenResponseDto> tokenFunc;

    GrantType(String description, BiFunction<TokenService, TokenRequestDto, TokenResponseDto> tokenFunc) {
        this.description = description;
        this.tokenFunc = tokenFunc;
    }

    @JsonCreator
    public static GrantType create(String value) {
        for (GrantType type : GrantType.values()) {
            if (type.name().equalsIgnoreCase(value))
                return type;
        }

        return null;
    }
}
