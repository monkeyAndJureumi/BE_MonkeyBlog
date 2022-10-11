package com.monkey.aggregate.user.infra.repository.custom;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.domain.UserProfile;
import com.monkey.aggregate.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final EntityManager em;

    public Optional<User> findByUserIdAndStatusIsActivate(UserId userId) {
        return em.createQuery(
                        "select u from User u where u.userId =:userId and u.status =:status",
                        User.class)
                .setParameter("userId", userId)
                .setParameter("status", UserStatus.ACTIVATE)
                .getResultStream()
                .findAny();
    }

    @Override
    public Optional<UserProfile> findProfileByUserId(UserId userId) {
        return em.createQuery(
                "select p from UserProfile p where p.userId =: userId"
                , UserProfile.class)
                .setParameter("userId", userId)
                .getResultStream().findAny();
    }
}
