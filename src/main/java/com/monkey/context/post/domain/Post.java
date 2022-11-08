package com.monkey.context.post.domain;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostUpdateDto;
import com.monkey.enums.CommonErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Embedded
    private PostAuthor author;

    @Column(name = "like_cnt")
    private long likeCnt;

    @OneToMany(mappedBy = "postLikeId.post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> likeList = new ArrayList<>();

    private boolean isSecret;

//    @ElementCollection
//    @CollectionTable(name = "post_comments", joinColumns = @JoinColumn(name = "id"))
//    @OrderColumn(name = "comment_id")
//    private Set<CommentId> commentIds = new LinkedHashSet<>();
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Builder
    public Post(PostAuthor postAuthor, PostSaveDto dto) {
        this.author = postAuthor;
        this.content = dto.getContent();
        this.isSecret = dto.getIsSecrete();
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void update(PostUpdateDto dto) {
        this.content = dto.getContent();
        this.isSecret = dto.getIsSecret();
        this.modifiedAt = LocalDateTime.now();
    }

    public void addLike(MemberId memberId) {
        this.likeList.add(new PostLike(memberId, this));
        this.likeCnt++;
    }

    public void deleteLike(MemberId memberId) {
        Optional<PostLike> result = this.likeList.stream()
                .filter(like -> like.getPostLikeId().getMemberId().equals(memberId)).findFirst();
        this.likeList.remove(result.orElseThrow(() -> new MonkeyException(CommonErrorCode.E404)));
        this.likeCnt--;
    }
}
