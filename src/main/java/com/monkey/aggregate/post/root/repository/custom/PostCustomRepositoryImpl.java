package com.monkey.aggregate.post.root.repository.custom;

import com.monkey.aggregate.comment.root.dto.CommentDto;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.dto.PostDto;
import com.monkey.aggregate.post.root.view.PostRes;
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
     * @param postId 게시글 인덱스번호
     * @return PostResponseDto 게시글, 댓글 응답객체
     */
    @Override
    public PostRes getResponseByPostId(PostId postId) {
        PostDto postDto = Optional.of(em.createQuery(
                        "select new com.monkey.aggregate.post.dto.PostDto(p.content, p.createdAt, p.modifiedAt) " +
                                "from Post p " +
                                "where p.id = :postId",
                        PostDto.class)
                .setParameter("postId", postId.getId())
                .getSingleResult()).orElseThrow(() -> new MonkeyException(ErrorCode.E100));

        /**
         댓글별 답글 존재여부 쿼리 발생
         List<Comment> comments = em.createQuery("select c from Comment c where c.postId = :postId order by c.createAt desc", Comment.class)
         .setParameter("postId", postId)
         .getResultList();

         List<CommentDto> commentDtoList = comments.stream()
         .map(comment -> new CommentDto(comment.getContent(), comment.getCreateAt(), commentRepository.existsByRefComment(comment)))
         .collect(Collectors.toList());
         */

        List<CommentDto> commentDtoList =
                em.createQuery(
                                "select new com.monkey.aggregate.comment.dto.CommentDto(c.content, c.createAt, c.hasReply) " +
                                        "from Comment c " +
                                        "where c.postId = :postId order by c.createAt desc ",
                                CommentDto.class)
                        .setParameter("postId", postId)
                        .getResultList();

        return new PostRes(postDto, commentDtoList);
    }
}
