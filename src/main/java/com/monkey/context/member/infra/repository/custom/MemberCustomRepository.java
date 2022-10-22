package com.monkey.context.member.infra.repository.custom;

import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.domain.MemberProfile;

import java.util.Optional;

public interface MemberCustomRepository {
    Optional<Members> findByUserIdAndStatusIsActivate(MemberId memberId);
    Optional<MemberProfile> findProfileByUserId(MemberId memberId);
}
