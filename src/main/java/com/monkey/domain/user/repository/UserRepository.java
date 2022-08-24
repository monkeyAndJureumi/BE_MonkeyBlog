package com.monkey.domain.user.repository;

import com.monkey.domain.user.entity.User;
import com.monkey.domain.user.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}
