package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.user.UserProfileSaveDto;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "userSkillId.profile", cascade = CascadeType.ALL)
    private Set<UserSkill> skillList = new LinkedHashSet<>();

    protected UserProfile(User user, UserProfileSaveDto dto) {
        this.user = user;
        this.skillList = dto.getSkillList().stream().map(skill -> new UserSkillId(this, skill)).collect(Collectors.toList())
                .stream().map(UserSkill::new).collect(Collectors.toSet());
    }

    protected void update(UserProfileUpdateDto dto) {
        this.skillList = dto.getSkillList().stream().map(skill -> new UserSkillId(this, skill)).collect(Collectors.toList())
                .stream().map(UserSkill::new).collect(Collectors.toSet());
    }
}
