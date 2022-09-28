package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.dto.token.RefreshRequestDto;
import com.monkey.aggregate.user.service.TokenService;
import com.monkey.aggregate.user.dto.token.TokenResponseDto;
import com.monkey.aggregate.user.dto.token.AccessRequestDto;
import com.monkey.aggregate.user.service.UserService;
import com.monkey.aggregate.user.validation.sequence.AccessSequence;
import com.monkey.aggregate.user.validation.sequence.RefreshSequence;
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
@RequestMapping("/user/token")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final TokenService tokenService;

    @ApiOperation(value = "액세스 토큰 발급", notes = "소셜로그인을 진행한 후 발급받은 액세스토큰을 요청 값에 담아 전송하면 서비스 액세스 토큰을 리턴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "액세스 토큰과 리프레시 토큰을 리턴"),
            @ApiResponse(code = 401, message = "소셜로그인을 진행한 후 발급받은 액세스 토큰이 만료 된 경우")
    })
    @PostMapping
    public ResponseEntity<TokenResponseDto> token(@Validated(AccessSequence.class) @RequestBody AccessRequestDto dto) {
        return new ResponseEntity<>(userService.provideAccessToken(dto), HttpStatus.OK);
    }

    @ApiIgnore
    @PatchMapping("/token")
    public ResponseEntity<TokenResponseDto> refresh(@Validated(RefreshSequence.class) @RequestBody RefreshRequestDto dto) {
        return new ResponseEntity<>(tokenService.refreshAccessToken(dto), HttpStatus.OK);
    }
}
