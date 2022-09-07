package com.monkey.aggregate.user.infra.client.kakao;

import com.monkey.aggregate.user.dto.social.kakao.KakaoAuthorizeCodeResponseDto;
import com.monkey.aggregate.user.dto.social.kakao.KakaoTokenResponseDto;
import org.springframework.util.MultiValueMap;

public interface KakaoAuthorizeWebClient {
    KakaoAuthorizeCodeResponseDto requestAuthorizeCode(MultiValueMap<String, String> parameters);
    KakaoTokenResponseDto requestToken(MultiValueMap<String, String> parameters);
}
