package com.monkey.aggregate.post_temp.service;

import com.monkey.aggregate.post_temp.domain.PostTemp;
import com.monkey.aggregate.post_temp.dto.PostTempSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostTempService {

    @Transactional
    public void save(PostTempSaveDto dto) {
        PostTemp post = PostTemp.builder()
                .userId(dto.getUserId())
                .content(dto.getContent())
                .build();
    }
}
