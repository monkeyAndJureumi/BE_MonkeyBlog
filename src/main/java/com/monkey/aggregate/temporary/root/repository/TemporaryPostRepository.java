package com.monkey.aggregate.temporary.root.repository;

import com.monkey.aggregate.temporary.root.entity.TemporaryPost;
import com.monkey.aggregate.temporary.root.entity.TemporaryPostId;
import com.monkey.aggregate.temporary.root.repository.custom.TemporaryPostCustomRepository;
import com.monkey.aggregate.user.root.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, TemporaryPostId>, TemporaryPostCustomRepository {
    Optional<TemporaryPost> findByPostIdAndUserId(TemporaryPostId postId, UserId userId);
}
