package com.monkey.aggregate.temporary.root.view;

import com.monkey.aggregate.temporary.root.dto.TemporaryPostIndexDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryPostIndexRes {
    private List<TemporaryPostIndexDto> data;

    public TemporaryPostIndexRes(List<TemporaryPostIndexDto> data) {
        this.data = data;
    }
}
