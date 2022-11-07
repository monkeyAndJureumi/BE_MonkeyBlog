package com.monkey.context.blog.infra.repository.custom;

import com.monkey.context.blog.infra.repository.query.BlogIndex;
import com.monkey.context.member.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogCustomRepositoryImpl implements BlogCustomRepository {
    private final EntityManager entityManager;

    @Override
    public List<BlogIndex> findAllIndexByMemberId(MemberId memberId) {
        return entityManager.createQuery(
                "select new com.monkey.context.blog.infra.repository.query.BlogIndex(b.blogId.id, b.name, b.description, b.createdAt) " +
                "from Blog b where b.blogOwner.memberId =: memberId", BlogIndex.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
