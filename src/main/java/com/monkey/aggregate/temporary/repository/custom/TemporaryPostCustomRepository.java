package com.monkey.aggregate.temporary.repository.custom;

import com.monkey.aggregate.temporary.dto.TemporaryPostIndexDto;
import com.monkey.aggregate.user.domain.UserId;

import java.util.List;

public interface TemporaryPostCustomRepository {
    List<TemporaryPostIndexDto> findAllIndexByUserId(UserId userId);
}
