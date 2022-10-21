package com.monkey.context.member.domain;

import com.monkey.context.member.dto.oauth.OAuthUserInfo;
import com.monkey.context.member.enums.MemberStatus;
import com.monkey.context.permission.implement.PermissionEntity;
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
public class Members implements PermissionEntity {
    @EmbeddedId
    private MemberId memberId;

    @OneToOne(mappedBy = "members", orphanRemoval = true, cascade = CascadeType.ALL)
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

    public Members(OAuthUserInfo userInfo) {
        LocalDateTime now = LocalDateTime.now();
        this.memberId = new MemberId(userInfo.getSocialType() + "_" + userInfo.getId());
        this.createdAt = now;
        this.modifiedAt = now;
        this.status = MemberStatus.ACTIVATE;
    }

    public void setProfile(MemberProfile profile) {
        profile.setMembers(this);
        this.profile = profile;
    }

    public void deactivate() {
        this.status = MemberStatus.DEACTIVATE;
    }
}
