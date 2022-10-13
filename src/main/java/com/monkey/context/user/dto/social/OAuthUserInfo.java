package com.monkey.context.user.dto.social;

import com.monkey.context.user.domain.UserId;
import com.monkey.context.user.enums.OauthType;

public abstract class OAuthUserInfo {
    public abstract OauthType getSocialType();
    public abstract Long getId();
    public abstract String getName();
    public abstract String getImageUrl();
    public abstract String getNickName();
    public abstract String getEmail();
    public abstract String getAgeRange();
    public abstract String getBirthDay();
    public abstract String getGender();
    public abstract String getPhoneNumber();

    public abstract UserId getUserId();
}
