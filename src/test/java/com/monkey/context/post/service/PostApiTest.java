package com.monkey.context.post.service;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostSaveDtoBuilder;
import com.monkey.context.post.infra.repository.PostRepository;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostResponseDto;
import com.monkey.context.temp_post.dto.TempPostSaveDto;
import com.monkey.context.temp_post.dto.TempPostSaveDtoBuilder;
import com.monkey.context.temp_post.infra.TempPostRepository;
import com.monkey.context.temp_post.service.TempPostService;
import com.monkey.exception.MonkeyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=local")
public class PostApiTest {
    @Autowired
    PostService postService;

    @Autowired
    TempPostService tempPostService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TempPostRepository tempPostRepository;


    public TempPostId saveTempPost() {
        TempPostSaveDto dto = TempPostSaveDtoBuilder.build("Temp Post Test");
        dto.setSession("Test User");
        return tempPostService.save(dto);
    }

    @DisplayName("게시글 저장 완료 / 임시 게시글 삭제 완료")
    @Test
    @Transactional
    @Rollback(value = false)
    public void postSaveWithDeleteTemp() {
        //given
        TempPostId tempPostId = saveTempPost();
        TempPostResponseDto tempPostResponseDto = tempPostService.select(new MemberId("Test User"), tempPostId);
        PostSaveDto postSaveDto = PostSaveDtoBuilder.create(tempPostResponseDto.getTempPostId(), tempPostResponseDto.getContent(), false);
        postSaveDto.setSession("Test User");

        //when
        PostId id = postService.save(postSaveDto);

        //then
        assertEquals(postRepository.findById(id.getId()).orElseThrow().getId(), 1L);
        assertEquals(tempPostRepository.findById(tempPostId).orElseThrow().getTempPostId(), tempPostId);
    }
}
