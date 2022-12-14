package com.monkey.context.member.controller;

import com.monkey.context.member.dto.member.MemberPatchRequestDto;
import com.monkey.context.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @Operation(description = "유저 프로필 수정/유저 비활성화")
    @PatchMapping
    public ResponseEntity<HttpStatus> patch(@Valid @RequestBody MemberPatchRequestDto dto) {
        dto.getGrantType().getConsumer().accept(memberService, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
