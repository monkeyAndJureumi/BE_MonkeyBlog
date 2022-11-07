package com.monkey.context.temp_post.service;

import com.monkey.context.grant.service.GrantService;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.temp_post.TempPostErrorCode;
import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostAuthor;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostIndexListDto;
import com.monkey.context.temp_post.dto.TempPostResponseDto;
import com.monkey.context.temp_post.infra.TempPostRepository;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TempPostQueryService {
    private final TempPostRepository tempPostRepository;
    private final GrantService permissionService;
    public TempPostResponseDto getTempPost(MemberId memberId, TempPostId id) {
        TempPost post = tempPostRepository.findById(id)
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.authorize(memberId, post.getAuthor().getMemberId());
        return new TempPostResponseDto(post);
    }

    public TempPostIndexListDto getIndexList(MemberId memberId, Pageable pageable) {
        Page<TempPost> result = tempPostRepository.findAllByAuthor(new TempPostAuthor(memberId), pageable);
        return new TempPostIndexListDto(result);
    }
}
