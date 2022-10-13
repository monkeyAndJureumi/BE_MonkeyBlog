package com.monkey.context.user.infra.repository;

import com.monkey.context.user.domain.User;
import com.monkey.context.user.domain.UserId;
import com.monkey.context.user.infra.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId>, UserCustomRepository {
}
