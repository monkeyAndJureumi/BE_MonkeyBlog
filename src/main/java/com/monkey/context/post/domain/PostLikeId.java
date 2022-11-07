package com.monkey.context.post.domain;

import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLikeId implements Serializable {
    private Long id;

    @AttributeOverride(name = "id", column = @Column(name = "member_id"))
    private MemberId memberId;

    public PostLikeId(Long id, MemberId memberId) {
        this.id = id;
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostLikeId that = (PostLikeId) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (memberId != null ? memberId.hashCode() : 0);
        return result;
    }
}
