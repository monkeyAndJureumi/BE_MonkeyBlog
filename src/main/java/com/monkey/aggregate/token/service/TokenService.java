package com.monkey.aggregate.token.service;

import com.monkey.aggregate.token.domain.Token;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.dto.TokenResponseDto;
import com.monkey.aggregate.token.dto.TokenSaveDto;
import com.monkey.aggregate.user.dto.user.UserIdentityDto;
import com.monkey.aggregate.token.infra.TokenRepository;
import com.monkey.aggregate.user.service.UserService;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;


@Service
@RequiredArgsConstructor
@Validated
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    public TokenResponseDto provideToken(@Valid TokenRequestDto dto) {
        UserIdentityDto identityDto = userService.getUserInfo(dto);
        TokenSaveDto saveDto = new TokenSaveDto(identityDto.getUserId(), identityDto.getUserCode(), jwtProperties);
        Token token = tokenRepository.findById(identityDto.getUserCode()).orElseGet(() -> tokenRepository.save(new Token(saveDto)));
        return new TokenResponseDto(token.getAccessToken(), jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());

    }

    public TokenResponseDto refreshToken(@Valid TokenRequestDto dto) {
        Token token = tokenRepository.findByRefreshToken(dto.getRefresh_token()).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E603));
        token.refresh(jwtProperties);
        return new TokenResponseDto(token.getAccessToken(), jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }
}
