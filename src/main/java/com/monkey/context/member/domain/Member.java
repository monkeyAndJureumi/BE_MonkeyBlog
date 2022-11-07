package com.monkey.context.member.domain;

import com.monkey.context.grant.enums.GrantGroup;
import com.monkey.context.member.dto.member.MemberProfileUpdateDto;
import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.enums.MemberStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "members")
public class Member {
    @EmbeddedId
    private MemberId memberId;

//    @OneToOne(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL)
    @Embedded
    @Comment("유저 프로필 정보")
    private MemberProfile profile;

    // 가입일
    @Column(name = "created_at", updatable = false)
    @Comment("유저 생성일자")
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    @Comment("유저 정보 수정일자")
    private LocalDateTime modifiedAt;

    @Column(name = "member_status")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private GrantGroup grantGroup;

    public Member(OAuthUserInfoDto userInfo) {
        LocalDateTime now = LocalDateTime.now();
        this.memberId = new MemberId(userInfo.getOAuthType() + "_" + userInfo.getId());
        this.createdAt = now;
        this.modifiedAt = now;
        this.status = MemberStatus.ACTIVATE;
        this.profile = new MemberProfile(userInfo);
        this.grantGroup = GrantGroup.USER;
    }

    public void updateProfile(MemberProfileUpdateDto updateDto) {
        this.profile.update(updateDto);
    }

    public void deactivate() {
        this.status = MemberStatus.DEACTIVATE;
    }
}
