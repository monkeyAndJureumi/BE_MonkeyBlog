package com.monkey.aggregate.post.domain;

import com.monkey.aggregate.post.enums.PostStatus;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class PostStatusConverter extends AbstractEnumConverter<PostStatus> {
    public PostStatusConverter(boolean isNullable, boolean isEncrypt) {
        super(PostStatus.class, false, false);
    }
}
