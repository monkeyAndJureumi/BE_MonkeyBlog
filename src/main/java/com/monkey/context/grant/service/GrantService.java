package com.monkey.context.grant.service;

import com.monkey.context.member.domain.MemberId;

import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrantService {
    public void authorize(MemberId memberId, MemberId targetId) {
        if (!memberId.equals(targetId))
            throw new MonkeyException(CommonErrorCode.E401);
    }
}
