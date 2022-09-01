package com.monkey.domain.temporary.root.repository.custom;

import com.monkey.domain.temporary.root.dto.TemporaryPostIndexDto;
import com.monkey.domain.user.root.entity.UserId;

import java.util.List;

public interface TemporaryPostCustomRepository {
    List<TemporaryPostIndexDto> findAllIndexByUserId(UserId userId);
}
