package com.monkey.aggregate.user.domain;

import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.converter.EncryptConverter;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Embedded
    private UserUId uid;

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

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "user_info", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private UserInfo userInfo;

    // 가입일
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Builder
    private User(UserUId uid, String name, String email, String number) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.number = number;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @Override
    public UserId getUserId() {
        return new UserId(this.getId());
    }
}
