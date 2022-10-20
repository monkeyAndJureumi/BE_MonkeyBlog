package com.monkey.context.post.dto;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.temp_post.domain.TempPostId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegisteredPost {
    private MemberId memberId;
    private TempPostId tempPostId;
}
