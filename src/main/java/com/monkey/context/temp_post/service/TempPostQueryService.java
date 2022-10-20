package com.monkey.context.temp_post.service;

import com.monkey.aop.permission.service.PermissionService;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TempPostQueryService {
    private final TempPostRepository tempPostRepository;
    private final PermissionService permissionService;
    public TempPostResponseDto getTempPost(MemberId memberId, TempPostId id) {
        TempPost post = tempPostRepository.findById(id)
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.checkPermission(memberId, post);
        return new TempPostResponseDto(post);
    }

    public TempPostIndexListDto getIndexList(MemberId memberId, int page, int size) {
        List<TempPost> result = tempPostRepository.findAllByAuthor(new TempPostAuthor(memberId), PageRequest.of(page, size, Sort.Direction.DESC));
        return new TempPostIndexListDto(result);
    }
}
