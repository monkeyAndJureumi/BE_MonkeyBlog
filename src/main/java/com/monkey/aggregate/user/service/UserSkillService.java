package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.user.UserSkillSearchResultDto;
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


    public UserSkillSearchResultDto find(String keyword) {
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
