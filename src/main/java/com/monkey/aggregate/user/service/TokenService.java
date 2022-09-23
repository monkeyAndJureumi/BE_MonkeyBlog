package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.domain.Token;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.dto.token.RefreshRequestDto;
import com.monkey.aggregate.user.dto.token.TokenResponseDto;
import com.monkey.aggregate.user.infra.repository.TokenRepository;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtProperties jwtProperties;

    public TokenResponseDto save(User user) {
        Token token = tokenRepository.save(Token.builder().userId(user.getUserId()).jwtProperties(jwtProperties).build());
        return createUserTokenDto(token);
    }

    public TokenResponseDto getToken(User user) {
        Token token = tokenRepository.findById(user.getUserId()).orElseGet(() -> createToken(user));
        return createUserTokenDto(token);
    }

    public Token createToken(User user) {
        Token token = Token.builder()
                .userId(user.getUserId())
                .jwtProperties(jwtProperties)
                .build();
        return tokenRepository.save(token);
    }

    /**
     * 리프레시 토큰을 비교하여 유효할 경우 액세스 토큰을 발급하여 반환, 만료될 경우 E602 예외발생 (재로그인 유도)
     * @param dto
     * @return
     */
    public TokenResponseDto refreshAccessToken(RefreshRequestDto dto) {
        Token token = tokenRepository.findByRefreshToken(dto.getRefreshToken()).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E603));
        token.refreshAccessToken(token.getUserId().getId(), dto.getRefreshToken(), jwtProperties);
        return createUserTokenDto(token);
    }

    private TokenResponseDto createUserTokenDto(Token token) {
        return new TokenResponseDto(token.getAccessToken(), token.getAccessTokenExpiration(), token.getRefreshToken(), token.getRefreshTokenExpiration());
    }
}
