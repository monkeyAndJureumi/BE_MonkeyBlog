package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KakaoController {
    private final KakaoService kakaoService;


}
