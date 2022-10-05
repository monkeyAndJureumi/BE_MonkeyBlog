package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.skill.enums.Skill;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserSkillId implements Serializable {
    private static final long serialVersionUID = 5484536627364259998L;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    @Column(name = "skill_name")
    @Enumerated(EnumType.STRING)
    private Skill skill;

    protected UserSkillId(UserProfile profile, Skill skill) {
        this.profile = profile;
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSkillId that = (UserSkillId) o;

        if (!Objects.equals(profile, that.profile)) return false;
        return skill == that.skill;
    }

    @Override
    public int hashCode() {
        int result = profile != null ? profile.hashCode() : 0;
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        return result;
    }
}
