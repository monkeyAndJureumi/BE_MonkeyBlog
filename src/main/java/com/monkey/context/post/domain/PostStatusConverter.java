package com.monkey.context.post.domain;

import com.monkey.context.post.enums.PostStatus;
import com.monkey.converter.AbstractEnumConverter;

import javax.persistence.Converter;

@Converter(autoApply = true)
public class PostStatusConverter extends AbstractEnumConverter<PostStatus> {
    public PostStatusConverter() {
        super(PostStatus.class, false, false);
    }
}
