package com.monkey.aggregate.temporary.root.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemporaryPostId implements Serializable {
    private static final long serialVersionUID = 7842243670110240599L;
    private String id;

    public TemporaryPostId(String id) {
        this.id = id;
    }
}
