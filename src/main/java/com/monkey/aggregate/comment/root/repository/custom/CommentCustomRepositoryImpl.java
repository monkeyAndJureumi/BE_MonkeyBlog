package com.monkey.aggregate.comment.root.repository.custom;

import com.monkey.aggregate.comment.root.dto.CommentResponseDto;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final EntityManager em;

    @Override
    public List<CommentResponseDto> findAllByPostId(UserId userId, PostId postId) {
        List<CommentResponseDto> result = em.createQuery(
                        "select new com.monkey.aggregate.comment.root.dto.CommentResponseDto(p.userId.id, c.userId.id, u.name, c.id, c.content, c.createdAt, c.hasReply, c.isSecrete) " +
                                "from Post p join fetch Comment c on p.id = c.postId.id join User u on u.id = c.userId.id " +
                                "where p.id = :postId and c.refComment is null order by c.createdAt desc",
                        CommentResponseDto.class)
                .setParameter("postId", postId.getId())
                .getResultList();
        result.forEach(comment -> comment.setSecreteContent(userId));
        return result;
    }

    @Override
    public List<CommentResponseDto> findAllByRefComment(UserId userId, CommentId commentId) {
        List<CommentResponseDto> result = em.createQuery(
                        "select new com.monkey.aggregate.comment.root.dto.CommentResponseDto(c2.userId.id, u.id, u.name, c1.id, c1.content, c1.createdAt, c1.hasReply, c1.isSecrete) " +
                                "from Comment c1 join Comment c2 on c1.refComment.id = c2.refComment.id join User u on u.id = c1.userId.id " +
                                "where c1.refComment.id = :commentId",
                        CommentResponseDto.class)
                .setParameter("commentId", commentId.getId())
                .getResultList();
        result.forEach(comment -> comment.setSecreteContent(userId));
        return result;
    }
}
