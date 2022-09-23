package com.monkey.aggregate.user.dto.social;

import com.monkey.aggregate.user.enums.SocialType;

public abstract class UserInfo {
    public abstract SocialType getSocialType();
    public abstract Long getId();
    public abstract String getName();
    public abstract String getImageUrl();
    public abstract String getNickName();
    public abstract String getEmail();
    public abstract String getAgeRange();
    public abstract String getBirthDay();
    public abstract String getGender();
    public abstract String getPhoneNumber();
}
