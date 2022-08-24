package com.monkey.domain.temporary.repository;

import com.monkey.domain.temporary.entity.TemporaryPost;
import com.monkey.domain.temporary.entity.TemporaryPostId;
import com.monkey.domain.temporary.repository.custom.TemporaryPostCustomRepository;
import com.monkey.domain.user.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, TemporaryPostId>, TemporaryPostCustomRepository {
    Optional<TemporaryPost> findByPostIdAndUserId(TemporaryPostId postId, UserId userId);
}
