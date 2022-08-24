package com.monkey.domain.post.repository;

import com.monkey.domain.post.entity.Post;
import com.monkey.domain.post.repository.custom.PostCustomRepository;
import com.monkey.domain.user.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    List<Post> findAllByUserId(UserId id);

    Optional<Post> findByUserIdAndId(UserId userId, Long postId);
}
