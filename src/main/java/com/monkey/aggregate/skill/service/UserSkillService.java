package com.monkey.aggregate.skill.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSkillService {
    private final StringRedisTemplate redisTemplate;
    private final String KEY = "test";


    public List find(String keyword) {
        keyword = keyword.toUpperCase();
        List<String> result = new ArrayList<>();
        int length = keyword.length();
        if (length == 0) return result;

        Long start = redisTemplate.opsForZSet().rank(KEY, keyword);
        log.info("keyword rank: {}", start);
        if (start == null) return result;

        Set<ZSetOperations.TypedTuple<String>> rangeResultWithScore = redisTemplate.opsForZSet().rangeWithScores(KEY, start, -1);
        log.info("range Result Size: {}", rangeResultWithScore.size());
        if (rangeResultWithScore.isEmpty()) return result;

        for(ZSetOperations.TypedTuple<String> typedTuple : rangeResultWithScore) {
            String value = typedTuple.getValue();
            log.info("search value: {}", value);
            log.info("search score: {}", typedTuple.getScore());
            int minLength = Math.min(value.length(), length);
            if (value.endsWith("*") && value.startsWith(keyword.substring(0, minLength))) {
                result.add(value.replace("*", ""));
            }
        }

        return result;
    }
}
