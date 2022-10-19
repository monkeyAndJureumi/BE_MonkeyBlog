package com.monkey.context.member.infra.repository;

import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.infra.repository.custom.UserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Members, MemberId>, UserCustomRepository {
}
