package com.monkey.domain.comment.entity.repository;

import com.monkey.domain.comment.root.dto.CommentDto;
import com.monkey.domain.comment.root.entity.Comment;
import com.monkey.domain.comment.root.repository.CommentRepository;
import com.monkey.domain.post.root.entity.Post;
import com.monkey.domain.post.root.entity.PostId;
import com.monkey.domain.post.root.repository.PostRepository;
import com.monkey.domain.user.root.entity.User;
import com.monkey.domain.user.root.entity.UserId;
import com.monkey.domain.user.root.enums.UserSocial;
import com.monkey.domain.user.root.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(readOnly = true)
public class CommentCustomRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @BeforeEach
    @Transactional
    public void init() {
        saveUser();
        savePost();
        saveComments();
    }

    @Test
    public void findAllByPostIdTest() {
        //given

        //when
        List<CommentDto> result = commentRepository.findAllByPostId(new UserId(1L), new PostId(1L));

        //then
        assertEquals(100, result.size());
        assertEquals("테스트1", result.get(0).getAuthor().getName());
        assertEquals("댓글-100", result.get(0).getContent());
    }

    public void saveComments() {
        List<Comment> comments = new ArrayList<>();
        for (var i = 1; i <= 100; i++) {
            comments.add(Comment.create(new UserId(1L), null, new PostId(1L), "댓글-" + i, false));
        }

        commentRepository.saveAll(comments);
    }

    public void saveUser() {
        List<User> users = new ArrayList<>();
        users.add(User.create("테스트1", "test1@gmail.com", "010-1111-1111", UserSocial.KAKAO));
        users.add(User.create("테스트2", "test2@gmail.com", "010-2222-2222", UserSocial.KAKAO));
        users.add(User.create("테스트3", "test3@gmail.com", "010-3333-3333", UserSocial.NAVER));
        users.add(User.create("테스트4", "test4@gmail.com", "010-4444-4444", UserSocial.NAVER));
        userRepository.saveAll(users);
    }

    public void savePost() {
        Post post = Post.create(new UserId(1L), "게시글", false);
        postRepository.save(post);
    }
}
