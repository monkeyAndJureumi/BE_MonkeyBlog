package com.monkey.context.blog.domain;

import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogOwner {
    @AttributeOverrides(
            @AttributeOverride(name = "id", column = @Column(name = "member_id", nullable = false))
    )
    private MemberId memberId;

    public BlogOwner(MemberId memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogOwner blogOwner = (BlogOwner) o;

        return Objects.equals(memberId, blogOwner.memberId);
    }

    @Override
    public int hashCode() {
        return memberId != null ? memberId.hashCode() : 0;
    }
}
