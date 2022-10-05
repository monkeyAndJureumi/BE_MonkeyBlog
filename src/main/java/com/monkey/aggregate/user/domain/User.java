package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileSaveDto;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.converter.EncryptConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class User implements PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "email", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String email;

    @Column(name = "number", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String number;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserInfo userInfo;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile profile;

    // 가입일
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Override
    public UserId getUserId() {
        return new UserId(this.getId());
    }

    public User(OAuthUserInfo userInfo) {
        this.name = userInfo.getName();
        this.code = userInfo.getSocialType() + "_" + userInfo.getId();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
        this.userInfo = new UserInfo(userInfo);
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void setProfile(UserProfileSaveDto dto) {
        this.profile = new UserProfile(this, dto);
    }

    public void updateProfile(UserProfileUpdateDto dto) {
        this.profile.update(dto);
    }
}
