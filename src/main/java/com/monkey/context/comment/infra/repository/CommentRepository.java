package com.monkey.context.comment.infra.repository;

import com.monkey.context.comment.domain.Comment;
import com.monkey.context.comment.infra.repository.custom.CommentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
}
