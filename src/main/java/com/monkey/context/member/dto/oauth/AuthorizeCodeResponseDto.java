package com.monkey.context.member.dto.oauth;

@Deprecated
public abstract class AuthorizeCodeResponseDto {
    public abstract String getCode();
    public abstract String getError();
}
