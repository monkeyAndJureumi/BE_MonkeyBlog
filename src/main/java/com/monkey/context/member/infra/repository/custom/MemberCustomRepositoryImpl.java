package com.monkey.context.member.infra.repository.custom;

import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.domain.MemberProfile;
import com.monkey.context.member.enums.MemberStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final EntityManager em;

    public Optional<Members> findByUserIdAndStatusIsActivate(MemberId memberId) {
        return em.createQuery(
                        "select u from Members u where u.memberId =:memberId and u.status =:status",
                        Members.class)
                .setParameter("memberId", memberId)
                .setParameter("status", MemberStatus.ACTIVATE)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<MemberProfile> findProfileByUserId(MemberId memberId) {
        return em.createQuery(
                "select p from MemberProfile p where p.memberId =: memberId"
                , MemberProfile.class)
                .setParameter("memberId", memberId)
                .getResultStream().findAny();
    }
}
