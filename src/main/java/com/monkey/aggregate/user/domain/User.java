package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.UserSocial;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String email;

    @Column(name = "number", unique = true)
    @Convert(converter = EncryptConverter.class)
    private String number;

    @Column(name = "social")
    @Convert(converter = UserSocialConverter.class)
    private UserSocial social;

    // 가입일
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 회원정보 수정일
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    private User(String name, String email, String number, UserSocial social) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.social = social;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public static User create(String name, String email, String number, UserSocial social) {
        return new User(name, email, number, social);
    }
}
