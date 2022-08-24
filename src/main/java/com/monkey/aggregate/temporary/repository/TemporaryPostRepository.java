package com.monkey.aggregate.temporary.repository;

import com.monkey.aggregate.temporary.entity.TemporaryPost;
import com.monkey.aggregate.temporary.entity.TemporaryPostId;
import com.monkey.aggregate.temporary.repository.custom.TemporaryPostCustomRepository;
import com.monkey.aggregate.user.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, TemporaryPostId>, TemporaryPostCustomRepository {
    Optional<TemporaryPost> findByPostIdAndUserId(TemporaryPostId postId, UserId userId);
}
