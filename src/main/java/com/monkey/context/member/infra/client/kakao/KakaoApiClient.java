package com.monkey.context.member.infra.client.kakao;

import com.monkey.context.member.dto.oauth.kakao.KakaoUserInfoResponseDto;
import org.springframework.util.MultiValueMap;

public interface KakaoApiClient {
    KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters);
}
