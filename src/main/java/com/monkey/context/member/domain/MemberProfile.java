package com.monkey.context.member.domain;

import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.member.MemberProfileUpdateDto;
import com.monkey.context.member.enums.MemberSkill;
import com.monkey.context.member.enums.MemberStatus;
import com.monkey.context.member.enums.OAuthType;
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
//@Entity
//@Table(name = "member_profile")
@Embeddable
public class MemberProfile {
//    @EmbeddedId
//    private MemberId memberId;

//    @MapsId
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private Member member;

    @Column(name = "oauth_type")
    private OAuthType oAuthType;

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

    public List<MemberSkill> getSkillList() {
        return Arrays.stream(skillList.split(", ")).map(MemberSkill::create).collect(Collectors.toList());
    }

    public MemberProfile(OAuthUserInfoDto userInfo) {
        this.oAuthType = userInfo.getOAuthType();
        this.name = userInfo.getName();
        this.imageUrl = userInfo.getImageUrl();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
    }

    public void update(MemberProfileUpdateDto dto) {
//        if (!this.member.getStatus().equals(MemberStatus.ACTIVATE))
//            throw new IllegalStateException("비활성화 된 유저입니다.");
        this.nickName = dto.getNickName();
        this.gitUrl = dto.getGitUrl();
        this.skillList = dto.getUserSkillList().stream()
                .map(skill -> Objects.requireNonNull(MemberSkill.create(skill)).name())
                .collect(Collectors.joining(", "));
    }

//    protected void setMember(Member member) {
//        this.member = member;
//    }
}
