package com.monkey.context.token.service;

import com.monkey.context.token.domain.Token;
import com.monkey.context.token.dto.*;
import com.monkey.context.token.enums.TokenErrorCode;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.token.infra.TokenRepository;
import com.monkey.context.member.service.MemberService;
import com.monkey.exception.MonkeyException;
import com.monkey.context.token.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Validated
public class TokenService {
    private final TokenRepository tokenRepository;
    private final MemberService memberService;
    private final JwtProperties jwtProperties;

    public TokenResponseDto provideToken(@Valid TokenAccessRequestDto dto) {
        MemberId memberId = memberService.getMember(dto.getOauthType(), dto.getAuthorizationCode());
        TokenSaveDto saveDto = new TokenSaveDto(memberId, jwtProperties);
        tokenRepository.save(new Token(saveDto));
        log.info("[{}] - Created AccessToken", memberId.getId());
        return new TokenResponseDto(saveDto.getAccessToken(), jwtProperties.getAccessTokenExpiration().intValue(), saveDto.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }

    public TokenResponseDto refreshToken(@Valid TokenRefreshRequestDto dto) {
        Token token = tokenRepository.findById(dto.getRefreshToken()).orElseThrow(() -> new MonkeyException(TokenErrorCode.T400));
        String accessToken = token.refresh(jwtProperties);
        log.info("Refresh Token");
        return new TokenResponseDto(accessToken, jwtProperties.getAccessTokenExpiration().intValue(), token.getRefreshToken(), jwtProperties.getRefreshTokenExpiration().intValue());
    }
}
