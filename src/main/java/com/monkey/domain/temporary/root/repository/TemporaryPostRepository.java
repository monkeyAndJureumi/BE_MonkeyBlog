package com.monkey.domain.temporary.root.repository;

import com.monkey.domain.temporary.root.entity.TemporaryPost;
import com.monkey.domain.temporary.root.entity.TemporaryPostId;
import com.monkey.domain.temporary.root.repository.custom.TemporaryPostCustomRepository;
import com.monkey.domain.user.root.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, TemporaryPostId>, TemporaryPostCustomRepository {
    Optional<TemporaryPost> findByPostIdAndUserId(TemporaryPostId postId, UserId userId);
}
