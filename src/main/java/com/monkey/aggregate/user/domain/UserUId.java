package com.monkey.aggregate.user.domain;

import com.monkey.aggregate.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserUId implements Serializable {
    private static final long serialVersionUID = 3453108518258495967L;

    @Column(name = "social_id")
    private String id;

    public UserUId(SocialType social, String id) {
        this.id = social.getName() + "_" + id;
    }
}
