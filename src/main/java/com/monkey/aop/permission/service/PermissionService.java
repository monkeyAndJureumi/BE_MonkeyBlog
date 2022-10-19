package com.monkey.aop.permission.service;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.context.member.domain.MemberId;

import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public void checkPermission(MemberId memberId, PermissionEntity permissionEntity) {
        if (!memberId.getId().equals(permissionEntity.getMemberId().getId()))
            throw new MonkeyException(CommonErrorCode.E401);
    }
}
