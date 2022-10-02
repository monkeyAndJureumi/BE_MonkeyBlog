package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.UserInfo;
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
    private com.monkey.aggregate.user.domain.UserInfo userInfo;

    // 가입일
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public User(UserInfo userInfo) {
        this.name = userInfo.getName();
        this.code = userInfo.getSocialType() + "_" + userInfo.getId();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.number = userInfo.getPhoneNumber();
        this.userInfo = new com.monkey.aggregate.user.domain.UserInfo(this.getUserId(), userInfo);
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @Override
    public UserId getUserId() {
        return new UserId(this.getId());
    }

}
