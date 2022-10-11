package com.monkey.aggregate.token.service;

import com.monkey.aggregate.token.domain.Token;
import com.monkey.aggregate.token.dto.*;
import com.monkey.aggregate.token.enums.TokenErrorCode;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.token.infra.TokenRepository;
import com.monkey.aggregate.user.service.UserService;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    public TokenResponseDto provideToken(TokenAccessRequestDto dto) {
        UserId userId = userService.getUserIdOrElseCreate(dto.getOauthType(), dto.getAccessToken());
        TokenSaveDto saveDto = new TokenSaveDto(userId, jwtProperties);
//        Token token = tokenRepository.findById(identityDto.getUserCode()).orElseGet(() -> tokenRepository.save(new Token(saveDto)));
        Token token = tokenRepository.save(new Token(saveDto));
        return new TokenResponseDto(saveDto.getAccessToken(), jwtProperties.getAccessTokenExpiration().intValue(), saveDto.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }

    public TokenResponseDto refreshToken(TokenRefreshRequestDto dto) {
        Token token = tokenRepository.findById(dto.getRefreshToken()).orElseThrow(() -> new MonkeyException(TokenErrorCode.T400, HttpStatus.BAD_REQUEST));
        String accessToken = token.refresh(jwtProperties);
        return new TokenResponseDto(accessToken, jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }
}
