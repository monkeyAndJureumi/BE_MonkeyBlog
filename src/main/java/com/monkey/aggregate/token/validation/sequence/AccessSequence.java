package com.monkey.aggregate.token.validation.sequence;

import com.monkey.aggregate.token.validation.groups.TokenRequestGroups;

import javax.validation.GroupSequence;

@GroupSequence({TokenRequestGroups.GrantType.class, TokenRequestGroups.SocialType.class, TokenRequestGroups.AccessToken.class})
public interface AccessSequence {
}
