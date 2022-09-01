package com.monkey.domain.comment.root.repository;

import com.monkey.domain.comment.root.entity.Comment;
import com.monkey.domain.comment.root.repository.custom.CommentCustomRepository;
import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.user.root.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
    Optional<Comment> findByUserIdAndId(UserId userId, Long commentId);
    List<Comment> findAllByPostIdAndRefCommentIsNullOrderByCreatedAtDesc(PostId postId);
}
