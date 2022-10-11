package com.monkey.aggregate.user.controller;

import com.monkey.aggregate.user.dto.user.UserSkillSearchResultDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/skill")
public class UserSkillController {
    private final StringRedisTemplate redisTemplate;
    private final String KEY = "test";

    @GetMapping
    public ResponseEntity<UserSkillSearchResultDto> search(@RequestParam("keyword") String keyword) {
        return new ResponseEntity<>(find(keyword), HttpStatus.OK);
    }

    private UserSkillSearchResultDto find(String keyword) {
        keyword = keyword.toUpperCase();
        List<String> result = new ArrayList<>();
        int length = keyword.length();
        if (length == 0) return new UserSkillSearchResultDto(result);

        Long start = redisTemplate.opsForZSet().rank(KEY, keyword);
        if (start == null) return new UserSkillSearchResultDto(result);

        Set<ZSetOperations.TypedTuple<String>> rangeResultWithScore = redisTemplate.opsForZSet().rangeWithScores(KEY, start, -1);
        if (rangeResultWithScore.isEmpty()) return new UserSkillSearchResultDto(result);

        for(ZSetOperations.TypedTuple<String> typedTuple : rangeResultWithScore) {
            String value = typedTuple.getValue();
            if (value.endsWith("*") && value.startsWith(keyword)) {
                result.add(value.replace("*", ""));
            }
        }

        return new UserSkillSearchResultDto(result);
    }
}
