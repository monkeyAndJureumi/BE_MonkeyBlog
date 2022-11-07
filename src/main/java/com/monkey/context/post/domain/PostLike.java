package com.monkey.context.post.domain;

import com.monkey.context.member.domain.MemberId;
import com.monkey.enums.ZoneId;
import com.monkey.utils.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "post_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike {
    @EmbeddedId
    private PostLikeId postLikeId;

    public PostLike(MemberId memberId) {
        this.postLikeId = new PostLikeId(DateTimeUtils.ConvertToMillis(LocalDateTime.now(), ZoneId.SEOUL), memberId);
    }
}
