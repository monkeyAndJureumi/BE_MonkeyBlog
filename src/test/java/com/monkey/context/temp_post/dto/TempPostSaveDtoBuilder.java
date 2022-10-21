package com.monkey.context.temp_post.dto;

public class TempPostSaveDtoBuilder {
    public static TempPostSaveDto build(String title, String content) {
        return new TempPostSaveDto(title, content);
    }
}
