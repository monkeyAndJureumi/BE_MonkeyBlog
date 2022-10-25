package com.monkey.context.member.dto.oauth;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.enums.OAuthType;

public abstract class OAuthUserInfoDto {
    public abstract OAuthType getOAuthType();
    public abstract Long getId();
    public abstract String getName();
    public abstract String getImageUrl();
    public abstract String getNickName();
    public abstract String getEmail();
    public abstract String getAgeRange();
    public abstract String getBirthDay();
    public abstract String getGender();
    public abstract String getPhoneNumber();
    public abstract MemberId getUserId();
}
