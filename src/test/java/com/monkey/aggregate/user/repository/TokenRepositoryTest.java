package com.monkey.aggregate.user.repository;

import com.monkey.aggregate.token.domain.Token;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.token.infra.TokenRepository;
import com.monkey.properties.JwtProperties;
import org.junit.jupiter.api.Test;
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
