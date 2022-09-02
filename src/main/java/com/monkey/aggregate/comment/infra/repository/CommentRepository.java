package com.monkey.aggregate.comment.infra.repository;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.infra.repository.custom.CommentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}
