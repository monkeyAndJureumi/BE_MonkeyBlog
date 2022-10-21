package com.monkey.context.token.controller;

import com.monkey.context.token.dto.TokenPostRequestDto;
import com.monkey.context.token.dto.TokenResponseDto;
import com.monkey.context.token.service.TokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth/v1/token")
@RequiredArgsConstructor
public class TokenRestController {
    private final TokenService tokenService;

    @ApiOperation(value = "토큰 발급/토큰 재발급(네이버 미지원)", notes = "소셜로그인을 진행한 후 발급받은 액세스토큰을 요청 값에 담아 전송하면 토큰을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "액세스 토큰과 리프레시 토큰을 리턴"),
            @ApiResponse(code = 400, message = "잘못된 값을 담아 요청한 경우"),
            @ApiResponse(code = 401, message = "소셜로그인을 진행한 후 발급받은 액세스 토큰이 만료 된 경우")
    })
    @PostMapping
    public ResponseEntity<TokenResponseDto> token(@Valid @RequestBody TokenPostRequestDto dto) {
        return new ResponseEntity<>(dto.getGrantType().getTokenFunc().apply(tokenService, dto), HttpStatus.OK);
    }
}