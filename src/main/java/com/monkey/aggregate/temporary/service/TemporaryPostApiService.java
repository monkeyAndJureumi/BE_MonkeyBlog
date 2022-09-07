package com.monkey.aggregate.temporary.service;

import com.monkey.aggregate.temporary.dto.TemporaryPostIndexDto;
import com.monkey.aggregate.temporary.dto.TemporaryPostIndexResponseDto;
import com.monkey.aggregate.temporary.repository.TemporaryPostRepository;
import com.monkey.aggregate.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TemporaryPostApiService {
    private final TemporaryPostRepository temporaryPostRepository;

    public TemporaryPostIndexResponseDto selectIndexList(UserId userId) {
        List<TemporaryPostIndexDto> result = temporaryPostRepository.findAllIndexByUserId(userId);
        return new TemporaryPostIndexResponseDto(result);
    }
}
