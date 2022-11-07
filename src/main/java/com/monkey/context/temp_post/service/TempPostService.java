package com.monkey.context.temp_post.service;

import com.monkey.context.temp_post.TempPostErrorCode;
import com.monkey.context.temp_post.domain.TempPost;
import com.monkey.context.temp_post.domain.TempPostAuthor;
import com.monkey.context.temp_post.domain.TempPostId;
import com.monkey.context.temp_post.dto.TempPostSaveDto;
import com.monkey.context.temp_post.dto.TempPostUpdateDto;
import com.monkey.context.temp_post.infra.TempPostRepository;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.grant.service.GrantService;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TempPostService {
    private final TempPostRepository tempPostRepository;
    private final GrantService permissionService;

    public TempPostId save(MemberId memberId, TempPostSaveDto dto) {
        TempPost post = new TempPost(new TempPostAuthor(memberId), dto);
        return tempPostRepository.save(post).getTempPostId();
    }

    public void update(MemberId memberId, TempPostUpdateDto dto) {
        TempPost post = tempPostRepository.findById(new TempPostId(dto.getPostTempId()))
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.authorize(memberId, post.getAuthor().getMemberId());
        post.update(dto);
    }

    public void delete(MemberId memberId, TempPostId tempPostId) {
        TempPost post = tempPostRepository.findById(tempPostId)
                .orElseThrow(() -> new MonkeyException(TempPostErrorCode.TP404));
        permissionService.authorize(memberId, post.getAuthor().getMemberId());
        tempPostRepository.delete(post);
    }
}
