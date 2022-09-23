package com.monkey.aggregate.user.validation.sequence;

import com.monkey.aggregate.user.validation.groups.OAuthValidateGroups;

import javax.validation.GroupSequence;

@GroupSequence({OAuthValidateGroups.GrantType.class, OAuthValidateGroups.SocialType.class, OAuthValidateGroups.NotNullAccessToken.class})
public interface AccessSequence {
}
