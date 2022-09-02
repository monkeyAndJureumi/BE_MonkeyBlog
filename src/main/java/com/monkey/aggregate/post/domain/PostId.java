package com.monkey.aggregate.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostId {
    @Column(name = "post_id")
    private Long id;

    public PostId(Long id) {
        this.id = id;

    }

}
