package com.monkey.context.member.infra.client.kakao;

import com.monkey.context.member.dto.oauth.kakao.KakaoTokenResponseDto;
import org.springframework.util.MultiValueMap;

public interface KakaoAuthClient {
    KakaoTokenResponseDto requestAccessToken(MultiValueMap<String, String> parameters);
}
