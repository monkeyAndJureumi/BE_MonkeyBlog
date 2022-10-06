package com.monkey.aggregate.token.service;

import com.monkey.aggregate.token.domain.Token;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.dto.TokenResponseDto;
import com.monkey.aggregate.token.dto.TokenSaveDto;
import com.monkey.aggregate.token.enums.TokenErrorCode;
import com.monkey.aggregate.user.dto.user.UserIdentityDto;
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

    public TokenResponseDto provideToken(TokenRequestDto dto) {
        UserIdentityDto identityDto = userService.getUserInfo(dto);
        TokenSaveDto saveDto = new TokenSaveDto(identityDto.getUserId(), jwtProperties);
//        Token token = tokenRepository.findById(identityDto.getUserCode()).orElseGet(() -> tokenRepository.save(new Token(saveDto)));
        Token token = tokenRepository.save(new Token(saveDto));
        return new TokenResponseDto(dto.getAccessToken(), jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }

    public TokenResponseDto refreshToken(TokenRequestDto dto) {
        Token token = tokenRepository.findByRefreshToken(dto.getRefreshToken()).orElseThrow(() -> new MonkeyException(TokenErrorCode.T400, HttpStatus.BAD_REQUEST));
        String accessToken = token.refresh(jwtProperties);
        return new TokenResponseDto(accessToken, jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }
}
