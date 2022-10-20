package com.monkey.context.post.dto;

public class PostSaveDtoBuilder {
    public static PostSaveDto create(String postTempId, String content, boolean isSecret) {
        return new PostSaveDto(postTempId, content, isSecret);
    }
}
