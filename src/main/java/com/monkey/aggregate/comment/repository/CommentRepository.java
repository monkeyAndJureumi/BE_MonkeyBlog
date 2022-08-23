package com.monkey.aggregate.comment.repository;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByUserIdAndId(UserId userId, Long commentId);
    List<Comment> findAllByPostIdOrderByCreateAtDesc(PostId postId);
    List<Comment> findAllByRefComment(Comment refComment);
}
