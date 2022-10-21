package com.monkey.context.comment.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentId {
    @Column(name = "comment_id")
    private Long value;
}
