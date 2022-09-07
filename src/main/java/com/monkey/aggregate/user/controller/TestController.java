package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.dto.social.kakao.KakaoAuthorizeCodeResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/kakao")
    public void test(@RequestBody KakaoAuthorizeCodeResponseDto dto) {
        System.out.println(dto.getCode());
    }
}
