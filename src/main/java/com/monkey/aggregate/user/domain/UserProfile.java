package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aggregate.user.enums.UserSkill;
import com.monkey.aggregate.user.enums.UserStatus;
import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.converter.EncryptConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_profile")
public class UserProfile implements PermissionEntity {
    @EmbeddedId
    private UserId userId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "git_url")
    private String gitUrl;

    @Column(name = "email", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String email;

    @Column(name = "number", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String number;

//    @OneToMany(mappedBy = "userSkillId.profile", cascade = CascadeType.ALL)
//    @ElementCollection
//    @CollectionTable(name = "user_skill", joinColumns = @JoinColumn(name = "userId"))
//    @OrderColumn
//    private Set<com.monkey.aggregate.user.enums.UserSkill> skillList = new LinkedHashSet<>();

    @Column(name = "skill_list")
    private String skillList;

    public List<UserSkill> getSkillList() {
        return Arrays.stream(skillList.split(", ")).map(UserSkill::create).collect(Collectors.toList());
    }

    public UserProfile(OAuthUserInfo userInfo) {
        this.name = userInfo.getName();
        this.imageUrl = userInfo.getImageUrl();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
    }

    public void update(UserProfileUpdateDto dto) {
        if (!this.user.getStatus().equals(UserStatus.ACTIVATE))
            throw new IllegalStateException("비활성화 된 유저입니다.");
        this.gitUrl = dto.getGitUrl();
        this.skillList = dto.getUserSkillList().stream()
                .map(skill -> Objects.requireNonNull(UserSkill.create(skill)).name())
                .collect(Collectors.joining(", "));
    }

    protected void setUser(User user) {
        this.user = user;
    }
}
