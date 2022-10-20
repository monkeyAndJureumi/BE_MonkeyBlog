package com.monkey.context.temp_post.enums;

import com.monkey.enums.ParamEnumerable;

public enum TempPostPageableProperties implements ParamEnumerable {
    MODIFIED_AT("modifiedAt"), CREATED_AT("createdAt");

    private final String value;

    TempPostPageableProperties(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getParam() {
        return name().toLowerCase();
    }
}
