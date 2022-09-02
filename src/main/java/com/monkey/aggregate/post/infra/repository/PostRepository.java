package com.monkey.aggregate.post.infra.repository;

import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.infra.repository.custom.PostCustomRepository;
import com.monkey.aggregate.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
}
