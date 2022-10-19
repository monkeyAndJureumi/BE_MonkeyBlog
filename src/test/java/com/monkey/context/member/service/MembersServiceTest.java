package com.monkey.context.member.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MembersServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("유저엔티티 저장")
    @Test
    public void saveUser() {

    }
}
