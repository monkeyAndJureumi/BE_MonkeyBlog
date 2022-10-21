package com.monkey.context.member.repository;

import com.monkey.context.token.infra.TokenRepository;
import com.monkey.context.token.properties.JwtProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=local")
public class TokenRepositoryTest {
    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    JwtProperties jwtProperties;
}
