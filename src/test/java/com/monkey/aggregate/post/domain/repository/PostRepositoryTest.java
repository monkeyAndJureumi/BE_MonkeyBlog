package com.monkey.aggregate.post.domain.repository;

import com.monkey.aggregate.post.domain.PostAuthor;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.enums.UserSocial;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.infra.repository.PostRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    private User initUser() {
        return userRepository.save(User.create("Test", "test@mail.com", "010-1111-1111", UserSocial.KAKAO));
    }

    private Post initPost(Long userId, String userName) {
        return postRepository.save(Post.create(new PostAuthor(new UserId(userId), userName), "Test Content", false));
    }

    @DisplayName("Post_조회")
    @Test
    @Transactional
    public void save() {
        //given
        User user = initUser();
        initPost(user.getId(), user.getName());

        //when
        Post result = postRepository.findById(1L).orElseThrow();

        //then
        assertEquals("Test Content", result.getContent());
    }

    @DisplayName("Post_수정")
    @Test
    @Transactional
    @Rollback(value = false)
    public void modify() {
        //given
        User user = initUser();
        initPost(user.getId(), user.getName());

        //when
        String text = "수정 후 내용";
        Post result = postRepository.findById(1L).orElseThrow();
        result.update(text, false);

        //then
        assertEquals(text, result.getContent());
    }

    @DisplayName("Post_삭제")
    @Test
    @Transactional
    public void delete() {
        //given
        User user = initUser();
        initPost(user.getId(), user.getName());

        //when
        Post result = postRepository.findById(1L).orElseThrow();
        postRepository.delete(result);
        MonkeyException exception = assertThrows(MonkeyException.class, () -> {
            postRepository.findById(1L).orElseThrow(() -> new MonkeyException(ErrorCode.E100));
        });

        //then
        assertEquals(ErrorCode.E100, exception.getErrorCode());
    }
}