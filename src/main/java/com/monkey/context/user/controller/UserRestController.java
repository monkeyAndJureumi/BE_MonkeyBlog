package com.monkey.context.user.controller;

import com.monkey.context.user.dto.user.UserPatchRequestDto;
import com.monkey.context.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @Operation(description = "유저 프로필 수정/유저 비활성화")
    @PatchMapping
    public ResponseEntity<HttpStatus> patch(@Valid @RequestBody UserPatchRequestDto dto) {
        dto.getGrantType().getConsumer().accept(userService, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
