package com.monkey.aggregate.post.domain;

import com.monkey.aggregate.user.domain.UserId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostAuthor implements Serializable {
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "author_id", nullable = false))
    )
    private UserId userId;

    @Column(name = "author_name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostAuthor postAuthor = (PostAuthor) o;

        return Objects.equals(userId.getId(), postAuthor.getUserId().getId());
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
