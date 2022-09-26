package com.monkey.aggregate.user.infra.repository;

import com.monkey.aggregate.user.domain.Token;
import com.monkey.aggregate.user.domain.UserId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findByRefreshToken(String accessToken);
}
