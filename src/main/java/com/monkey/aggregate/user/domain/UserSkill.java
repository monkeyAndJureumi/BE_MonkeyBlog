package com.monkey.aggregate.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_skill")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSkill {
    @EmbeddedId
    private UserSkillId userSkillId;

    protected UserSkill(UserSkillId userSkillId) {
        this.userSkillId = userSkillId;
    }
}
