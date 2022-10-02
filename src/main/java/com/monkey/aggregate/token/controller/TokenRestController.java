package com.monkey.aggregate.token.controller;

import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.token.dto.TokenResponseDto;
import com.monkey.aggregate.token.service.TokenService;
import com.monkey.aggregate.token.validation.sequence.AccessSequence;
import com.monkey.aggregate.token.validation.sequence.RefreshSequence;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/auth/v1/token")
@RequiredArgsConstructor
public class TokenRestController {
    private final TokenService tokenService;

    @ApiOperation(value = "토큰 발급", notes = "소셜로그인을 진행한 후 발급받은 액세스토큰을 요청 값에 담아 전송하면 토큰을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "액세스 토큰과 리프레시 토큰을 리턴"),
            @ApiResponse(code = 400, message = "토큰타입이 일치하지 않는 경우"),
            @ApiResponse(code = 401, message = "소셜로그인을 진행한 후 발급받은 액세스 토큰이 만료 된 경우")
    })
    @PostMapping
    public ResponseEntity<TokenResponseDto> token(@Validated(AccessSequence.class) @RequestBody TokenRequestDto dto) {
        return new ResponseEntity<>(tokenService.provideToken(dto), HttpStatus.OK);
    }

    @ApiIgnore
    @PatchMapping
    public ResponseEntity<TokenResponseDto> refresh(@Validated(RefreshSequence.class) @RequestBody TokenRequestDto dto) {
        return new ResponseEntity<>(tokenService.refreshToken(dto), HttpStatus.OK);
    }
}
