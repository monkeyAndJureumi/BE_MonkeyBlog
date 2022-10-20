package com.monkey.context.temp_post.enums;

import com.monkey.enums.ParamEnumerable;
import org.springframework.data.domain.Sort;

public enum TempPostPageableSort implements ParamEnumerable {
    ASC(Sort.Direction.ASC), DESC(Sort.Direction.DESC);

    private final Sort.Direction direction;

    TempPostPageableSort(Sort.Direction direction) {
        this.direction = direction;
    }

    public Sort.Direction getValue() {
        return direction;
    }

    @Override
    public String getParam() {
        return name().toLowerCase();
    }
}
