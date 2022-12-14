package com.monkey.context.token.validator.sequence;

import com.monkey.context.token.validator.groups.TokenRequestGroups;

import javax.validation.GroupSequence;

@GroupSequence(TokenRequestGroups.RefreshToken.class)
public interface RefreshRequestSequence {
}
