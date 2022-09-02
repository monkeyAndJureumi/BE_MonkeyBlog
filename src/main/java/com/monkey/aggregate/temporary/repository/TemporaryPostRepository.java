package com.monkey.aggregate.temporary.repository;

import com.monkey.aggregate.temporary.domain.TemporaryPost;
import com.monkey.aggregate.temporary.domain.TemporaryPostId;
import com.monkey.aggregate.temporary.repository.custom.TemporaryPostCustomRepository;
import com.monkey.aggregate.user.domain.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, TemporaryPostId>, TemporaryPostCustomRepository {
    Optional<TemporaryPost> findByPostIdAndUserId(TemporaryPostId postId, UserId userId);
}
