package com.monkey.context.token.infra;

import com.monkey.context.token.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
