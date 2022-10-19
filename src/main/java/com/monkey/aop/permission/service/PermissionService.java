package com.monkey.aop.permission.service;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.exception.MemberErrorCode;
import com.monkey.context.member.exception.MemberException;

import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public void checkPermission(MemberId memberId, PermissionEntity permissionEntity) {
        if (!memberId.getId().equals(permissionEntity.getMemberId().getId()))
            throw new MemberException(MemberErrorCode.M401);
    }
}
