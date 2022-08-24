package com.monkey.domain.temporary.repository.custom;

import com.monkey.domain.temporary.dto.TemporaryPostIndexDto;
import com.monkey.domain.user.entity.UserId;

import java.util.List;

public interface TemporaryPostCustomRepository {
    List<TemporaryPostIndexDto> findAllIndexByUserId(UserId userId);
}
