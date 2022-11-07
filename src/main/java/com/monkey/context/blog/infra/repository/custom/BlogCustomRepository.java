package com.monkey.context.blog.infra.repository.custom;

import com.monkey.context.blog.infra.repository.query.BlogIndex;
import com.monkey.context.member.domain.MemberId;

import java.util.List;

public interface BlogCustomRepository {
    List<BlogIndex> findAllIndexByMemberId(MemberId memberId);
}
