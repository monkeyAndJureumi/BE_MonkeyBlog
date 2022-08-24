package com.monkey.domain.temporary.repository.custom;

import com.monkey.domain.temporary.dto.TemporaryPostIndexDto;
import com.monkey.domain.user.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TemporaryPostCustomRepositoryImpl implements TemporaryPostCustomRepository {
    private final EntityManager em;

    @Override
    public List<TemporaryPostIndexDto> findAllIndexByUserId(UserId userId) {
        return em.createQuery("select new com.monkey.domain.temporary.dto.TemporaryPostIndexDto(p.postId.id, p.createdAt, p.modifiedAt) " +
                "from TemporaryPost p where p.userId = :userId", TemporaryPostIndexDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
