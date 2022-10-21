package com.monkey.context.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberId implements Serializable {
    private static final long serialVersionUID = -7032849755577754587L;
    @Column(name = "id")
    @Comment(value = "회원 아이디")
    private String id;

    public MemberId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MemberId memberId = (MemberId) o;

        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "{" + "=" + id.hashCode() + '}';
    }
}
