package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
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
    private UserId id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id")
    @Comment("소셜 유저정보")
    private UserInfo userInfo;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
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

    @Override
    public UserId getUserId() {
        return this.id;
    }

    public User(OAuthUserInfo userInfo) {
        this.id = new UserId(userInfo.getSocialType() + "_" + userInfo.getId());
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void setUserInfo(UserInfo userInfo) {
        userInfo.setUser(this);
        this.userInfo = userInfo;
    }

    public void setProfile(UserProfile profile) {
        profile.setUser(this);
        this.profile = profile;
    }

    public void updateProfile(UserProfileUpdateDto dto) {
        this.profile.update(dto);
        this.modifiedAt = LocalDateTime.now();
    }
}
