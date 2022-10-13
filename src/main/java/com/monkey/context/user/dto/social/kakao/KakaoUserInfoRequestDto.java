package com.monkey.context.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoUserInfoRequestDto {
    @JsonProperty("secure_resource")
    private Boolean secureResource;

    @JsonProperty("property_keys")
    private String property_keys;

    public KakaoUserInfoRequestDto(Boolean secureResource, List<String> property_keys) {
        this.secureResource = secureResource;
        this.property_keys = "[\"" + String.join("\",\"", property_keys) + "\"]";
    }
}
