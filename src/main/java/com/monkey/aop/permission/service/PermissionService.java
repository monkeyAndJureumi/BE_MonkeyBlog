package com.monkey.aop.permission.service;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    public void checkPermission(UserId userId, PermissionEntity permissionEntity) {
        if (userId.getId() != permissionEntity.getUserId().getId())
            throw new MonkeyException(ErrorCode.E001, HttpStatus.UNAUTHORIZED);
    }
}
