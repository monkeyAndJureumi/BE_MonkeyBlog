package com.monkey.aggregate.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class KakaoUserInfoRequestDto {
    @JsonProperty("secure_resource")
    private Boolean secureResource;

    @JsonProperty("property_keys")
    private String[] property_keys;
}
