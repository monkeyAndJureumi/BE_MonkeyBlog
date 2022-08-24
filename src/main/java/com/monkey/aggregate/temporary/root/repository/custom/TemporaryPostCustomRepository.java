package com.monkey.aggregate.temporary.root.repository.custom;

import com.monkey.aggregate.temporary.root.dto.TemporaryPostIndexDto;
import com.monkey.aggregate.user.root.entity.UserId;

import java.util.List;

public interface TemporaryPostCustomRepository {
    List<TemporaryPostIndexDto> findAllIndexByUserId(UserId userId);
}
