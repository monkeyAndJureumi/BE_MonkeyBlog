package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.converter.EncryptConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "user_info")
public class UserInfo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Convert(converter = UserSocialConverter.class)
    private SocialType social;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "email")
    @Convert(converter = EncryptConverter.class)
    private String email;

    @Column(name = "age_range")
    private String ageRange;

    @Column(name = "birthday")
    private String birthDay;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    @Convert(converter = EncryptConverter.class)
    private String phoneNumber;
}
