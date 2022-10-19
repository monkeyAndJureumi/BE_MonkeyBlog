package com.monkey.context.post.domain;

import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostUpdateDto;
import com.monkey.aop.permission.implement.PermissionEntity;
import com.monkey.context.member.domain.MemberId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "post")
public class Post implements PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Embedded
    private PostAuthor author;

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
    public Post(PostSaveDto dto) {
        this.author = new PostAuthor(dto.getMemberId());
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

    @Override
    public MemberId getMemberId() {
        return this.author.getMemberId();
    }
}
