package com.monkey.aggregate.post_temp.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PostTempId implements Serializable {
    private static final long serialVersionUID = 511665815035422515L;
    private String id;

    public PostTempId(String userCode) {
        this.id = LocalDateTime.now() + "_" + userCode;
    }
}
