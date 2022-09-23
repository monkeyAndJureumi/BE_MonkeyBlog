package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.dto.token.RefreshRequestDto;
import com.monkey.aggregate.user.service.TokenService;
import com.monkey.aggregate.user.dto.token.TokenResponseDto;
import com.monkey.aggregate.user.dto.token.AccessRequestDto;
import com.monkey.aggregate.user.service.UserService;
import com.monkey.aggregate.user.validation.sequence.AccessSequence;
import com.monkey.aggregate.user.validation.sequence.RefreshSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenResponseDto> token(@Validated(AccessSequence.class) @RequestBody AccessRequestDto dto) {
        return new ResponseEntity<>(userService.provideAccessToken(dto), HttpStatus.OK);
    }

    @PatchMapping("/token")
    public ResponseEntity<TokenResponseDto> refresh(@Validated(RefreshSequence.class) @RequestBody RefreshRequestDto dto) {
        return new ResponseEntity<>(tokenService.refreshAccessToken(dto), HttpStatus.OK);
    }
}
