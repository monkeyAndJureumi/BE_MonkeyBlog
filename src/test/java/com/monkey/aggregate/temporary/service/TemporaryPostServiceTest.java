package com.monkey.aggregate.temporary.service;

import com.monkey.aggregate.temporary.domain.TemporaryPost;
import com.monkey.aggregate.temporary.repository.TemporaryPostRepository;
import com.monkey.aggregate.temporary.dto.TemporaryPostSaveDto;
import com.monkey.aggregate.user.domain.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TemporaryPostServiceTest {
    @InjectMocks
    private TemporaryPostService temporaryPostService;

    @Mock
    private TemporaryPostRepository repository;

    @DisplayName("임시_게시글_저장")
    @Test
    public void save() {
        //given
        TemporaryPostSaveDto dto = new TemporaryPostSaveDto("Test Temporary");
        dto.setSession(1L);
        TemporaryPost post = getTemporaryPost();

        //when
        temporaryPostService.savePost(dto);

        //verify
        verify(repository, times(1)).save(post);
    }

    private TemporaryPost getTemporaryPost() {
        return TemporaryPost.builder()
                .userId(new UserId(1L))
                .content("Test Temporary")
                .build();
    }
}
