package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.UserSkill;
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
    private UserSkill userSkill;

    protected UserSkillId(UserProfile profile, UserSkill userSkill) {
        this.profile = profile;
        this.userSkill = userSkill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSkillId that = (UserSkillId) o;

        if (!Objects.equals(profile, that.profile)) return false;
        return userSkill == that.userSkill;
    }

    @Override
    public int hashCode() {
        int result = profile != null ? profile.hashCode() : 0;
        result = 31 * result + (userSkill != null ? userSkill.hashCode() : 0);
        return result;
    }
}
