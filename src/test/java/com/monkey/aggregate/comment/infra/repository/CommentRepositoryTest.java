package com.monkey.aggregate.comment.infra.repository;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.domain.CommentAuthor;
import com.monkey.aggregate.comment.domain.CommentId;
import com.monkey.aggregate.comment.dto.CommentDto;
import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.domain.PostAuthor;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.infra.repository.PostRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
public class CommentRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    private User initUser() {
        User user = User.builder()
                .name("Test")
                .email("test@mail.com")
                .number("010-1111-1111")
                .build();
        return userRepository.save(user);
    }

    private Post initPost(Long userId) {
        return postRepository.save(Post.builder()
                .author(new PostAuthor(new UserId(userId)))
                .content("Test Content")
                .isSecret(false)
                .build());
    }

    @DisplayName("findById 메서드 테스트")
    @Test
    public void test1() {
        //given
        User user = initUser();
        Post post = initPost(user.getId());
        Comment comment = Comment.builder()
                .author(new CommentAuthor(new UserId(user.getId())))
                .postId(new PostId(post.getId()))
                .refComment(null)
                .content("Test Content")
                .isSecrete(false)
                .build();
        commentRepository.save(comment);

        //when
        Comment result = commentRepository.findById(1L).orElseThrow();

        //then
        assertEquals(comment.getContent(), result.getContent());
    }

    @DisplayName("findAllByPostId 메서드 테스트")
    @Test
    public void test3() {
        //given
        User user = initUser();
        Post post = initPost(user.getId());
        Comment comment = Comment.builder()
                .author(new CommentAuthor(new UserId(user.getId())))
                .postId(new PostId(post.getId()))
                .refComment(null)
                .content("Test Content")
                .isSecrete(false)
                .build();
        commentRepository.save(comment);

        //when
        List<CommentDto> result = commentRepository.findAllByPostId(new PostId(post.getId()));

        //then
        assertEquals(1, result.size());
        assertEquals(user.getName(), result.get(0).getAuthor().getName());
    }

    @DisplayName("findAllByRefCommentId 메서드 테스트")
    @Test
    public void test4() {
        //given
        User user = initUser();
        Post post = initPost(user.getId());
        Comment refComment = Comment.builder()
                .author(new CommentAuthor(new UserId(user.getId())))
                .postId(new PostId(post.getId()))
                .refComment(null)
                .content("Test Content")
                .isSecrete(false)
                .build();
        commentRepository.save(refComment);

        Comment replyComment = Comment.builder()
                .author(new CommentAuthor(new UserId(user.getId())))
                .postId(new PostId(post.getId()))
                .refComment(refComment)
                .content("Test Reply Content")
                .isSecrete(false)
                .build();
        commentRepository.save(replyComment);

        //when
        List<CommentDto> result = commentRepository.findAllByRefCommentId(new CommentId(refComment.getId()));

        //then
        assertEquals(1, result.size());
        assertEquals("Test Reply Content", result.get(0).getContent());
    }
}
