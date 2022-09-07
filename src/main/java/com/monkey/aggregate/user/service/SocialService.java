package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.TokenResponseDto;

public interface SocialService {
    TokenResponseDto getTokenDto(String authorizeCode);
}
