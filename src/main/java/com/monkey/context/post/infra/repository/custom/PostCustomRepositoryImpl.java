package com.monkey.context.post.infra.repository.custom;

import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.dto.PostResponseDto;
import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.enums.CommonErrorCode;
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
     * * 게시글 인덱스 번호로 조회
     *
     * @param postId 게시글 인덱스번호
     * @return PostResponseDto 게시글 응답 객체
     */
    @Override
    public Optional<PostResponseDto> selectByPostId(PostId postId) {
        List<PostResponseDto> result = em.createQuery(
                        "select new com.monkey.context.post.dto.PostResponseDto(u.memberId.id, u.profile.name, p.content, p.createdAt, p.modifiedAt) " +
                                "from Post p join Member u on p.author.memberId.id = u.memberId.id " +
                                "where p.id = :postId",
                        PostResponseDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public void deleteTempPostById(TempPostId tempPostId) {
        TempPost result = em.createQuery(
                        "select p " +
                                "from TempPost p " +
                                "where p.tempPostId =: id",
                        TempPost.class)
                .setParameter("id", tempPostId)
                .getResultList().stream().findAny()
                .orElseThrow(() -> new MonkeyException("temp post does not exist", CommonErrorCode.E404));
        em.remove(result);
    }
}
