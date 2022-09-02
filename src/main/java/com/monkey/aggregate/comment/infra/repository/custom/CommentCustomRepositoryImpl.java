package com.monkey.aggregate.comment.infra.repository.custom;

import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final EntityManager em;

    @Override
    public List<CommentDto> findAllByPostId(UserId userId, PostId postId) {
        List<CommentDto> result = em.createQuery(
                        "select new com.monkey.aggregate.comment.dto.CommentDto(p.author.userId.id, c.author.userId.id, u.name, c.id, c.content, c.createdAt, c.hasReply, c.isSecrete) " +
                                "from Post p join fetch Comment c on p.id = c.postId.id join User u on u.id = c.author.userId.id " +
                                "where p.id = :postId and c.refComment is null order by c.createdAt desc",
                        CommentDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();
        return setSecretComment(result, userId);
    }

    @Override
    public List<CommentDto> findAllByRefCommentId(UserId userId, CommentId commentId) {
        List<CommentDto> result = em.createQuery(
                        "select new com.monkey.aggregate.comment.dto.CommentDto(c2.author.userId.id, u.id, u.name, c1.id, c1.content, c1.createdAt, c1.hasReply, c1.isSecrete) " +
                                "from Comment c1 join Comment c2 on c1.refComment.id = c2.refComment.id join User u on u.id = c1.author.userId.id " +
                                "where c1.refComment.id = :commentId",
                        CommentDto.class)
                .setParameter("commentId", commentId.getValue())
                .getResultList();
        return setSecretComment(result, userId);
    }

    private List<CommentDto> setSecretComment(List<CommentDto> comments, UserId userId) {
        comments.forEach(comment -> comment.setSecreteContent(userId));
        return comments;
    }
}
