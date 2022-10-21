package com.monkey.context.comment.infra.repository.custom;

import com.monkey.context.comment.domain.CommentId;
import com.monkey.context.comment.dto.CommentDto;
import com.monkey.context.post.domain.PostId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final EntityManager em;

    @Override
    public List<CommentDto> findAllByPostId(PostId postId) {
        List<CommentDto> result = em.createQuery(
                        "select new com.monkey.context.comment.dto.CommentDto(p.author.memberId.id, c.author.memberId.id, u.profile.name, c.id, c.content, c.createdAt, c.hasReply, c.isSecrete) " +
                                "from Post p join fetch Comment c on p.id = c.postId.id join Members u on u.memberId.id = c.author.memberId.id " +
                                "where p.id = :postId and c.refComment is null order by c.createdAt desc",
                        CommentDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();
        return result;
    }

    @Override
    public List<CommentDto> findAllByRefCommentId(CommentId commentId) {
        List<CommentDto> result = em.createQuery(
                        "select new com.monkey.context.comment.dto.CommentDto(c2.author.memberId.id, u.memberId.id, u.profile.name, c1.id, c1.content, c1.createdAt, c1.hasReply, c1.isSecrete) " +
                                "from Comment c1 join Comment c2 on c1.refComment.id = c2.refComment.id join Members u on u.memberId.id = c1.author.memberId.id " +
                                "where c1.refComment.id = :commentId",
                        CommentDto.class)
                .setParameter("commentId", commentId.getValue())
                .getResultList();
        return result;
    }
}
