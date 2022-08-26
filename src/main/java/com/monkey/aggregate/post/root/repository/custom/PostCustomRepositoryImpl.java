package com.monkey.aggregate.post.root.repository.custom;

import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.dto.PostDto;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
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
     * * 게시글 인덱스 번호로 게시글과 댓글(오름차순)을 조회
     *
     * @param postId 게시글 인덱스번호
     * @return PostResponseDto 게시글, 댓글 응답객체
     */
    @Override
    public PostDto selectByPostId(PostId postId) {
        List<PostDto> result = em.createQuery(
                        "select new com.monkey.aggregate.post.root.dto.PostDto(u.id, u.name, p.content, p.createdAt, p.modifiedAt) " +
                                "from Post p join User u on p.userId.id = u.id " +
                                "where p.id = :postId",
                        PostDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();

        return result.stream().findAny().orElseThrow(() -> new MonkeyException(ErrorCode.E100));
    }
}
