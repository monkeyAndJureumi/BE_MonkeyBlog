package com.monkey.aggregate.post.infra.repository;

import com.monkey.aggregate.post.domain.PostAuthor;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.dto.PostResponseDto;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    private User initUser() {
        User user = User.builder()
                .name("Test")
                .email("test@mail.com")
                .number("010-1111-1111")
                .build();
        return userRepository.save(user);
    }

    private Post initPost(Long userId, String userName) {
        return postRepository.save(Post.builder()
                .author(new PostAuthor(new UserId(userId)))
                .content("Test Content")
                .isSecret(false)
                .build());
    }

    @DisplayName("selectByPostId 테스트")
    @Test
    public void test1() {
        //given
        User user = initUser();
        Post post = initPost(user.getId(), user.getName());

        //when
        PostResponseDto result = postRepository.selectByPostId(new PostId(1L)).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E100));

        //then
        assertEquals(user.getName(), result.getAuthor().getName());
        assertEquals(post.getContent(), result.getContent());
    }
}