package com.monkey.context.post.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostId implements Serializable {
    private static final long serialVersionUID = 6818613266734795508L;
    @Column(name = "post_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequenceGenerator")
    @SequenceGenerator(name = "post_sequenceGenerator", sequenceName = "post_sequence")
    private Long id;

    public PostId(Long id) {
        this.id = id;

    }

}
