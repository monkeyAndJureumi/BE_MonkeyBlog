package com.monkey.aggregate.user.root.repository;

import com.monkey.aggregate.user.root.entity.User;
import com.monkey.aggregate.user.root.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
}
