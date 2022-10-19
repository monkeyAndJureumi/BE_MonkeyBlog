package com.monkey.context.post_temp.service;

import com.monkey.context.post_temp.domain.PostTemp;
import com.monkey.context.post_temp.domain.PostTempId;
import com.monkey.context.post_temp.dto.PostTempResponseDto;
import com.monkey.context.post_temp.dto.PostTempSaveDto;
import com.monkey.context.post_temp.dto.PostTempUpdateDto;
import com.monkey.context.post_temp.infra.PostTempRepository;
import com.monkey.context.member.domain.MemberId;
import com.monkey.aop.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostTempService {
    private final PostTempRepository postTempRepository;
    private final PermissionService permissionService;

    public PostTempResponseDto select(MemberId memberId, String id) {
        PostTemp post = postTempRepository.findById(new PostTempId(id)).orElseThrow();
        permissionService.checkPermission(memberId, post);
        return new PostTempResponseDto(post);
    }

    @Transactional
    public void save(PostTempSaveDto dto) {
        PostTemp post = new PostTemp(dto);
        postTempRepository.save(post);
    }

    @Transactional
    public void update(PostTempUpdateDto dto) {
        PostTemp post = postTempRepository.findById(new PostTempId(dto.getPostTempId())).orElseThrow();
        permissionService.checkPermission(dto.getMemberId(), post);
        post.update(dto);
    }

    @Transactional
    public void delete(MemberId memberId, String id) {
        PostTemp post = postTempRepository.findById(new PostTempId(id)).orElseThrow();
        permissionService.checkPermission(memberId, post);
        postTempRepository.delete(post);
    }
}
