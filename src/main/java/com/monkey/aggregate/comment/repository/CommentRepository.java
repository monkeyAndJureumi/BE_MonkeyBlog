package com.monkey.aggregate.comment.repository;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.repository.custom.CommentCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentCustomRepository {
    List<Comment> findAllByRefComment_Id(Long commentId);
}
