package com.monkey.aggregate.comment.domain;

import com.monkey.aggregate.user.domain.UserId;
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
    private UserId userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentAuthor that = (CommentAuthor) o;

        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
