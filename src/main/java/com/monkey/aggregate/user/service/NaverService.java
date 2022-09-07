package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverService implements SocialService {
    @Override
    public TokenResponseDto getTokenDto(String authorizeCode) {
        return null;
    }
}
