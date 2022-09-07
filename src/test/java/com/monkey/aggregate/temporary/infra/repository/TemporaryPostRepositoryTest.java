package com.monkey.aggregate.temporary.infra.repository;

import com.monkey.aggregate.temporary.domain.TemporaryPost;
import com.monkey.aggregate.temporary.dto.TemporaryPostIndexDto;
import com.monkey.aggregate.temporary.repository.TemporaryPostRepository;
import com.monkey.aggregate.user.domain.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class TemporaryPostRepositoryTest {
    @Autowired
    private TemporaryPostRepository temporaryPostRepository;


    @DisplayName("findAllIndexByUserId 테스트")
    @Test
    public void 임시게시글_목록() {
        //given
        saveIndexList();
        UserId userId = new UserId(1L);

        //when
        List<TemporaryPostIndexDto> result = temporaryPostRepository.findAllIndexByUserId(userId);

        //then
        assertEquals(3, result.size());

    }

    private void saveIndexList() {
        temporaryPostRepository.save(TemporaryPost.builder().userId(new UserId(1L)).content("Test1").build());
        temporaryPostRepository.save(TemporaryPost.builder().userId(new UserId(2L)).content("Test2").build());
        temporaryPostRepository.save(TemporaryPost.builder().userId(new UserId(1L)).content("Test3").build());
        temporaryPostRepository.save(TemporaryPost.builder().userId(new UserId(2L)).content("Test4").build());
        temporaryPostRepository.save(TemporaryPost.builder().userId(new UserId(1L)).content("Test5").build());
    }
}
