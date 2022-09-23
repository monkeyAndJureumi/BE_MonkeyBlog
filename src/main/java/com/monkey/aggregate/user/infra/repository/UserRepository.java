package com.monkey.aggregate.user.infra.repository;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.aggregate.user.infra.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByUserInfoIdAndAndUserInfoSocial(Long id, SocialType social);
}
