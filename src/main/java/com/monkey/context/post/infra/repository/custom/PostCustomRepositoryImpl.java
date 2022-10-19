package com.monkey.context.post.infra.repository.custom;

import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostResponseDto;
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
                        "select new com.monkey.context.post.dto.PostResponseDto(u.memberId.id, u.profile.name, p.content, p.createdAt, p.modifiedAt) " +
                                "from Post p join Members u on p.author.memberId.id = u.memberId.id " +
                                "where p.id = :postId",
                        PostResponseDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();

        return result.stream().findAny();
    }
}
