package com.monkey.aggregate.post.entity.repository;

import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.repository.CommentRepository;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.view.PostRes;
import com.monkey.aggregate.post.root.view.PostUpdateReq;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.aggregate.user.root.enums.UserSocial;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
import com.monkey.aggregate.post.root.entity.Post;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.user.root.entity.User;
import com.monkey.aggregate.user.root.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @DisplayName("PostDtoRepositoryTest_초기화")
    @BeforeEach
    @Transactional
    public void init() {
        // 유저 저장
        User user = User.create("이지호", "test@gmail.com", "010-1111-1111", UserSocial.KAKAO);
        User user2 = User.create("이지호2", "test2@gmail.com", "010-1234-5678", UserSocial.NAVER);
        userRepository.save(user);
        userRepository.save(user2);

        // 게시글 저장
        Post post = Post.create(new UserId(user.getId()), "게시글1");
        postRepository.save(post);

        // 댓글 저장
        Comment comment1 = Comment.create(new UserId(user2.getId()), null, new PostId(post.getId()), "댓글1");
        Comment comment2 = Comment.create(new UserId(user.getId()), comment1, null, "대댓글1-1");
        Comment comment3 = Comment.create(new UserId(user.getId()), null, new PostId(post.getId()), "댓글2");

        commentRepository.save(comment1);
        commentRepository.save(comment2);
        commentRepository.save(comment3);
    }

    @DisplayName("Post_저장")
    @Test
    @Transactional
    public void save() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = Post.create(new UserId(user.getId()), "게시글");
        postRepository.save(post);

        //when
        Post result = postRepository.findById(1L).orElseThrow();

        //then
        assertEquals(post.getContent(), result.getContent());
    }

    @DisplayName("Post_수정")
    @Test
    @Transactional
    @Rollback(value = false)
    public void modify() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = Post.create(new UserId(user.getId()), "게시글");
        postRepository.save(post);

        //when
        String text = "업데이트";
        PostUpdateReq req = new PostUpdateReq(1L, text);
        Post result = postRepository.findById(req.getPostId()).orElseThrow();
        result.update(req);

        //then
        assertEquals(text, result.getContent());
    }

    @DisplayName("Post_삭제")
    @Test
    @Transactional
    public void delete() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = Post.create(new UserId(user.getId()), "게시글");
        postRepository.save(post);

        //when
        Post result = postRepository.findById(1L).orElseThrow(() -> new MonkeyException(ErrorCode.E100, "Not Found Post Entity"));
        postRepository.delete(result);

        postRepository.findById(1L).orElseThrow(() -> new MonkeyException(ErrorCode.E100, "Not Found Post Entity"));

        //then
        assertThrows(MonkeyException.class, () -> {

        });
    }

    @DisplayName("유저별_ Post_조회")
    @Test
    @Transactional
    public void findByUser() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        createPosts(user.getId());

        //when
        List<Post> result = postRepository.findAllByUserId(new UserId(user.getId()));

        //then
        assertEquals(11, result.size());
    }

    @DisplayName("게시글_댓글_출력")
    @Test
    public void Post_Comment() {
        //given
        PostId postId = new PostId(1L);

        //when
        PostRes response = postRepository.getResponseByPostId(postId);

        //then
        assertEquals(response.getContent(), "게시글1");
        assertEquals(response.getComments().size(), 2);
        assertEquals(false, response.getComments().get(0).isHasReply());
        assertEquals(true, response.getComments().get(1).isHasReply());
    }

    private void createPosts(Long userId) {
        for(var i = 0; i < 10; i++) {
            Post post = Post.create(new UserId(userId), "게시글" + i);
            postRepository.save(post);
        }
    }
}