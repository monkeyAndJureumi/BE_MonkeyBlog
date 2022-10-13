package com.monkey.aop.permission.service;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.context.user.domain.UserId;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public void checkPermission(UserId userId, PermissionEntity permissionEntity) {
        if (!userId.getId().equals(permissionEntity.getUserId().getId()))
            throw new MonkeyException(MonkeyErrorCode.E001, HttpStatus.UNAUTHORIZED);
    }
}
