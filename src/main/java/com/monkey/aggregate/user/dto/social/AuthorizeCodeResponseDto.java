package com.monkey.aggregate.user.dto.social;

@Deprecated
public abstract class AuthorizeCodeResponseDto {
    public abstract String getCode();
    public abstract String getError();
}
