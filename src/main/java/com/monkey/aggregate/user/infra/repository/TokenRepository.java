package com.monkey.aggregate.user.infra.repository;

import com.monkey.aggregate.user.domain.Token;
import com.monkey.aggregate.user.domain.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<Token, UserId> {
    Optional<Token> findByRefreshToken(String accessToken);
    Optional<Token> findTopByUserId(UserId userId);
}
