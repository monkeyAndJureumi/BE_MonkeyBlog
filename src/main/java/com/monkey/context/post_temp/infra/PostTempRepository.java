package com.monkey.context.post_temp.infra;

import com.monkey.context.post_temp.domain.PostTemp;
import com.monkey.context.post_temp.domain.PostTempId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTempRepository extends JpaRepository<PostTemp, PostTempId> {
}
