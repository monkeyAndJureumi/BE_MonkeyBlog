package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.enums.SocialType;
import com.monkey.converter.EncryptConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name = "user_info")
public class UserInfo {
    @Id
    private Long id;

    @OneToOne(mappedBy = "userInfo")
//    @JoinTable(name = "user_info", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "id"))
    private User user;

    @Column(name = "type")
    @Convert(converter = UserSocialConverter.class)
    @Comment("소셜 타입")
    private SocialType social;

    @Column(name = "name")
    @Comment("이름")
    private String name;

    @Column(name = "image_url")
    @Comment("프로필 이미지 URL")
    private String imageUrl;

    @Column(name = "nickname")
    @Comment("닉네임")
    private String nickName;

    @Column(name = "email")
    @Convert(converter = EncryptConverter.class)
    @Comment("이메일 주소")
    private String email;

    @Column(name = "age_range")
    @Comment("연령대")
    private String ageRange;

    @Column(name = "birthday")
    @Comment("생년월일")
    private String birthDay;

    @Column(name = "gender")
    @Comment("성별")
    private String gender;

    @Column(name = "phone_number")
    @Convert(converter = EncryptConverter.class)
    @Comment("휴대전화번호")
    private String phoneNumber;

    public UserInfo(OAuthUserInfo userInfo) {
        this.id = userInfo.getId();
        this.social = userInfo.getSocialType();
        this.name = userInfo.getName();
        this.imageUrl = userInfo.getImageUrl();
        this.nickName = userInfo.getNickName();
        this.email = userInfo.getEmail();
        this.ageRange = userInfo.getAgeRange();
        this.birthDay = userInfo.getBirthDay();
        this.gender = userInfo.getGender();
        this.phoneNumber = userInfo.getPhoneNumber();
    }

    protected void setUser(User user) {
        this.user = user;
    }
}
