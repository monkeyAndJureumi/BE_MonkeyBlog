package com.monkey.context.temp_post.service;

import com.monkey.context.temp_post.TempPostErrorCode;
import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostResponseDto;
import com.monkey.context.temp_post.dto.TempPostSaveDto;
import com.monkey.context.temp_post.dto.TempPostUpdateDto;
import com.monkey.context.temp_post.infra.TempPostRepository;
import com.monkey.context.member.domain.MemberId;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TempPostService {
    private final TempPostRepository tempPostRepository;
    private final PermissionService permissionService;

    public TempPostResponseDto select(MemberId memberId, TempPostId id) {
        TempPost post = tempPostRepository.findById(id)
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.checkPermission(memberId, post);
        return new TempPostResponseDto(post);
    }

    @Transactional
    public TempPostId save(TempPostSaveDto dto) {
        TempPost post = new TempPost(dto);
        return tempPostRepository.save(post).getTempPostId();
    }

    @Transactional
    public void update(TempPostUpdateDto dto) {
        TempPost post = tempPostRepository.findById(new TempPostId(dto.getPostTempId()))
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.checkPermission(dto.getMemberId(), post);
        post.update(dto);
    }

    @Transactional
    public void delete(MemberId memberId, TempPostId tempPostId) {
        TempPost post = tempPostRepository.findById(tempPostId)
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.checkPermission(memberId, post);
        tempPostRepository.delete(post);
    }
}
