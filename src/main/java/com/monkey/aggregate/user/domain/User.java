package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aggregate.user.enums.UserStatus;
import com.monkey.aop.permission.implement.PermissionEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class User implements PermissionEntity {
    @EmbeddedId
    private UserId userId;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @Comment("유저 프로필 정보")
    private UserProfile profile;

    // 가입일
    @Column(name = "created_at", updatable = false)
    @Comment("유저 생성일자")
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    @Comment("유저 정보 수정일자")
    private LocalDateTime modifiedAt;

    @Column(name = "status")
    private UserStatus status;

    public User(OAuthUserInfo userInfo) {
        this.userId = new UserId(userInfo.getSocialType() + "_" + userInfo.getId());
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.status = UserStatus.ACTIVATE;
    }

    public void setProfile(UserProfile profile) {
        profile.setUser(this);
        this.profile = profile;
    }

    public void deactivate() {
        this.status = UserStatus.DEACTIVATE;
    }
}
