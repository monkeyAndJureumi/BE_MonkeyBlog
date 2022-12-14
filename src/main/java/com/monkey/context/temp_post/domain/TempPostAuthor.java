package com.monkey.context.temp_post.domain;

import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
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
public class
TempPostAuthor implements Serializable {
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "author_id", nullable = false))
    )
    private MemberId memberId;

    public TempPostAuthor(MemberId memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TempPostAuthor postAuthor = (TempPostAuthor) o;

        return Objects.equals(memberId.getId(), postAuthor.getMemberId().getId());
    }

    @Override
    public int hashCode() {
        int result = memberId != null ? memberId.hashCode() : 0;
        return result;
    }
}
