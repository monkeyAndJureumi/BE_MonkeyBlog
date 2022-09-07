package com.monkey.aggregate.user.infra.client.kakao;

import com.monkey.aggregate.user.dto.social.kakao.KakaoUserInfoResponseDto;
import org.springframework.util.MultiValueMap;

public interface KakaoApiWebClient {
    KakaoUserInfoResponseDto requestUserInfo(String accessToken, MultiValueMap<String, String> parameters);
}
