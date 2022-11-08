package com.monkey.context.post.repository;

import com.monkey.context.member.domain.MemberId;
import com.monkey.context.post.domain.Post;
import com.monkey.context.post.domain.PostAuthor;
import com.monkey.context.post.domain.PostId;
import com.monkey.context.post.domain.PostLike;
import com.monkey.context.post.dto.PostSaveDto;
import com.monkey.context.post.dto.PostSaveDtoBuilder;
import com.monkey.context.post.infra.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = "spring.profiles.active=local")
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @DisplayName("게시글 좋아요 증가")
    @Test
    @Rollback(value = false)
    public void addLikeCnt() {
        //given
        postRepository.save(createPost());
        Post post = postRepository.findById(1L).orElseThrow();
        MemberId memberId = new MemberId("Test Member");
        post.addLike(memberId);

        //when
        Post result = postRepository.findById(1L).orElseThrow();
        List<PostLike> likeList = result.getLikeList();

        //then
        assertEquals(likeList.get(0).getPostLikeId().getMemberId(), memberId);
    }

    @DisplayName("게시글 좋아요 감소")
    @Test
    @Rollback(value = false)
    public void deleteLike() {
        //given
        Post post = createPost();
        postRepository.save(post);
        MemberId memberId = new MemberId("Test Member");
        post.addLike(memberId);

        Post result = postRepository.findById(1L).orElseThrow();
        result.deleteLike(memberId);

        assertEquals(result.getLikeCnt(), 0L);
    }

    private Post createPost() {
        MemberId memberId1 = new MemberId("Test Member");
        PostSaveDto dto1 = PostSaveDtoBuilder.create(null, "Test Content", false);
        Post post1 = new Post(new PostAuthor(memberId1), dto1);
        return post1;
    }
}
