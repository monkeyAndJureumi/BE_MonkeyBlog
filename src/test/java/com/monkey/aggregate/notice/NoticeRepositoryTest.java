package com.monkey.aggregate.notice;

import com.monkey.aggregate.notice.domain.Notice;
import com.monkey.aggregate.notice.repository.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Transactional(readOnly = true)
public class NoticeRepositoryTest {
    @Autowired
    NoticeRepository noticeRepository;

    @BeforeEach
    @Transactional
    public void init() {
        List<Notice> notices = new ArrayList<>();
        for (var i = 0; i < 100; i++) {
            notices.add(new Notice("공지사항 제목 - " + (i + 1), "공지사항 내용 - " + (i + 1)));
        }

        noticeRepository.saveAll(notices);
    }

    @Test
    public void pageableSelect() {
        //given
        Pageable pageRequest = PageRequest.of(-1, 5, Sort.by("createdAt").descending());

        //when
        Page<Notice> noticePage = noticeRepository.findAll(pageRequest);

        //then
        assertEquals(100, noticePage.getTotalElements());
        assertEquals(20, noticePage.getTotalPages());
        assertEquals(1, noticePage.getNumber());
        assertEquals("공지사항 제목 - 95", noticePage.getContent().get(0).getTitle());
    }
}
