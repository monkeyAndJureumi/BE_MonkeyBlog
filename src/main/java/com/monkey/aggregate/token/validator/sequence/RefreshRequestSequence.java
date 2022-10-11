package com.monkey.aggregate.token.validator.sequence;

import com.monkey.aggregate.token.validator.groups.TokenRequestGroups;

import javax.validation.GroupSequence;

@GroupSequence(TokenRequestGroups.RefreshToken.class)
public interface RefreshRequestSequence {
}
