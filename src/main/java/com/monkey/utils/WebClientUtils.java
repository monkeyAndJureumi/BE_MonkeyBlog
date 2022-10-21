package com.monkey.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebClientUtils {

    public static MultiValueMap<String, String> convertParameters(Object dto, ObjectMapper objectMapper) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            Map<String, String> buffer = objectMapper.convertValue(dto, new TypeReference<>() {
            });
            params.setAll(buffer);
            return params;
        } catch (Exception e) {
            log.error("파라미터 변환 중 에러발생: {}", e.getMessage());
            throw new IllegalStateException("서버 오류");
        }
    }
}
