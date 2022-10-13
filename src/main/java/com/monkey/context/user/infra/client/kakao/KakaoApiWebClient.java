package com.monkey.context.user.infra.client.kakao;

import com.monkey.context.user.dto.social.kakao.KakaoUserInfoResponseDto;
import org.springframework.util.MultiValueMap;

public interface KakaoApiWebClient {
    KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters);
}
