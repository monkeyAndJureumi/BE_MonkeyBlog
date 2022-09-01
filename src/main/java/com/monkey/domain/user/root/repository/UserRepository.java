package com.monkey.domain.user.root.repository;

import com.monkey.domain.user.root.entity.User;
import com.monkey.domain.user.root.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}
