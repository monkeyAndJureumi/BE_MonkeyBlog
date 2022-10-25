package com.monkey.context.member.infra.repository;

import com.monkey.context.member.domain.Member;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.infra.repository.custom.MemberCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberId>, MemberCustomRepository {
}
