package com.monkey.aggregate.post.infra.repository.custom;

import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {
    private final EntityManager em;

    /**
     * * 게시글 인덱스 번호로 조회
     *
     * @param postId 게시글 인덱스번호
     * @return PostResponseDto 게시글 응답 객체
     */
    @Override
    public Optional<PostResponseDto> selectByPostId(PostId postId) {
        List<PostResponseDto> result = em.createQuery(
                        "select new com.monkey.aggregate.post.dto.PostResponseDto(u.id, u.name, p.content, p.createdAt, p.modifiedAt) " +
                                "from Post p join User u on p.author.userId.id = u.id " +
                                "where p.id = :postId",
                        PostResponseDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();

        return result.stream().findAny();
    }
}
