package com.monkey.context.member.domain;

import com.monkey.context.member.dto.oauth.OAuthUserInfo;
import com.monkey.context.member.dto.user.UserProfileUpdateDto;
import com.monkey.context.member.enums.UserSkill;
import com.monkey.context.member.enums.MemberStatus;
import com.monkey.context.permission.implement.PermissionEntity;
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
@Table(name = "member_profile")
public class MemberProfile implements PermissionEntity {
    @EmbeddedId
    private MemberId memberId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Members members;

    @Column(name = "member_name")
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

    @Column(name = "phone_number", unique = true)
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

    public MemberProfile(OAuthUserInfo userInfo) {
        this.name = userInfo.getName();
        this.imageUrl = userInfo.getImageUrl();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
    }

    public void update(UserProfileUpdateDto dto) {
        if (!this.members.getStatus().equals(MemberStatus.ACTIVATE))
            throw new IllegalStateException("비활성화 된 유저입니다.");
        this.nickName = dto.getNickName();
        this.gitUrl = dto.getGitUrl();
        this.skillList = dto.getUserSkillList().stream()
                .map(skill -> Objects.requireNonNull(UserSkill.create(skill)).name())
                .collect(Collectors.joining(", "));
    }

    protected void setMembers(Members members) {
        this.members = members;
    }
}
