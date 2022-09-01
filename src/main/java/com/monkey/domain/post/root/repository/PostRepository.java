package com.monkey.domain.post.root.repository;

import com.monkey.domain.post.root.entity.Post;
import com.monkey.domain.post.root.repository.custom.PostCustomRepository;
import com.monkey.domain.user.root.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    List<Post> findAllByUserId(UserId id);

    Optional<Post> findByUserIdAndId(UserId userId, Long postId);
}
