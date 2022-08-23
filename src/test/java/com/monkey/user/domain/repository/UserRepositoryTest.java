package com.monkey.user.domain.repository;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.repository.UserRepository;
import com.monkey.aggregate.user.enums.UserSocial;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @DisplayName("소셜 저장")
    @Test
    @Transactional
    public void saveUser() {
        //given
        User user = User.create("이지호", "test@email.com", "010-1111-1111", UserSocial.NAVER);
        userRepository.save(user);

        //when
        User result = userRepository.findById(1L).orElseThrow();

        //then
        assertEquals(UserSocial.NAVER.getDescription(), result.getSocial().getDescription());
    }
}
