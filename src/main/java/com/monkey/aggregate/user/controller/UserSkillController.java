package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.dto.user.UserSkillSearchResultDto;
import com.monkey.aggregate.user.service.UserSkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/skill")
@Slf4j
public class UserSkillController {
    private final UserSkillService service;

    @GetMapping
    public ResponseEntity<UserSkillSearchResultDto> search(@RequestParam("keyword") String keyword) {
        log.info("{}", keyword);
        return new ResponseEntity<>(service.find(keyword), HttpStatus.OK);
    }
}
