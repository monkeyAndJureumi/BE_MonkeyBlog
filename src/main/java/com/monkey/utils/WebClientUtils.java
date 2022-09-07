package com.monkey.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
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
            Map<String, String> buffer = objectMapper.convertValue(dto, new TypeReference<>() {});
            params.setAll(buffer);
            return params;
        } catch (Exception e) {
            throw new MonkeyException(MonkeyErrorCode.E500, e.getMessage());
        }
    }
}
