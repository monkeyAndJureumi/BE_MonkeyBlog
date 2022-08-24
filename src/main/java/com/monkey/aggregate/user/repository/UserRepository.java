package com.monkey.aggregate.user.repository;

import com.monkey.aggregate.user.entity.User;
import com.monkey.aggregate.user.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}
