package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileSaveDto;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.converter.EncryptConverter;
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
    @EmbeddedId
    private UserId id;

    @MapsId
    @OneToOne(mappedBy = "profile")
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

    @OneToMany(mappedBy = "userSkillId.profile", cascade = CascadeType.ALL)
    private Set<UserSkill> skillList = new LinkedHashSet<>();

    public UserProfile(OAuthUserInfo userInfo) {
        this.name = userInfo.getName();
        this.imageUrl = userInfo.getImageUrl();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
    }

    protected void update(UserProfileUpdateDto dto) {
        this.gitUrl = dto.getGitUrl();
        this.skillList = dto.getSkillList().stream().map(skill -> new UserSkillId(this, skill)).collect(Collectors.toList())
                .stream().map(UserSkill::new).collect(Collectors.toSet());
    }

    protected void setUser(User user) {
        this.user = user;
    }
}
