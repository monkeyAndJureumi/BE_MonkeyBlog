package com.monkey.aop.permission.service;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.context.member.domain.MemberId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public void checkPermission(MemberId memberId, PermissionEntity permissionEntity) {
        if (!memberId.getId().equals(permissionEntity.getMemberId().getId()))
            throw new MonkeyException(MonkeyErrorCode.E001, HttpStatus.UNAUTHORIZED);
    }
}
