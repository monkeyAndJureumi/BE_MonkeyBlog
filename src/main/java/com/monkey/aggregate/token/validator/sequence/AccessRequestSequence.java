package com.monkey.aggregate.token.validator.sequence;

import com.monkey.aggregate.token.validator.groups.TokenRequestGroups;

import javax.validation.GroupSequence;

@GroupSequence({TokenRequestGroups.OAuthType.class, TokenRequestGroups.AccessToken.class})
public interface AccessRequestSequence {
}
