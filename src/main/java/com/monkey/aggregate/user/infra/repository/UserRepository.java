package com.monkey.aggregate.user.infra.repository;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.infra.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}
