package com.monkey.aggregate.comment.root.repository;

import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.repository.custom.CommentCustomRepository;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.user.root.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
    Optional<Comment> findByUserIdAndId(UserId userId, Long commentId);
    List<Comment> findAllByPostIdAndRefCommentIsNullOrderByCreatedAtDesc(PostId postId);
}