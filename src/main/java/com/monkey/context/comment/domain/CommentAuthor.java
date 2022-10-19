package com.monkey.context.comment.domain;

import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Getter
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentAuthor {
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "author_id", nullable = false))
    )
    private MemberId memberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentAuthor that = (CommentAuthor) o;

        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return memberId != null ? memberId.hashCode() : 0;
    }
}
