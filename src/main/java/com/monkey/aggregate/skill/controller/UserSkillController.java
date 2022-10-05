package com.monkey.aggregate.skill.controller;

import com.monkey.aggregate.skill.dto.UserSkillSearchResultDto;
import com.monkey.aggregate.skill.service.UserSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/skill")
public class UserSkillController {
    private final UserSkillService service;

    @GetMapping
    public ResponseEntity<List<String>> search(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(service.find(keyword), HttpStatus.OK);
    }
}
