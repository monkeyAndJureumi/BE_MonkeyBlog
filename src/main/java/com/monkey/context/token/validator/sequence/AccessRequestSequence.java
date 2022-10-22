package com.monkey.context.token.validator.sequence;

import com.monkey.context.token.validator.groups.TokenRequestGroups;

import javax.validation.GroupSequence;

@GroupSequence({TokenRequestGroups.OAuthType.class, TokenRequestGroups.AuthorizationCode.class})
public interface AccessRequestSequence {
}
