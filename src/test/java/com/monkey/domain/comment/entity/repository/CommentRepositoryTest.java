package com.monkey.domain.comment.entity.repository;

import com.monkey.domain.comment.entity.Comment;
import com.monkey.domain.comment.repository.CommentRepository;
import com.monkey.domain.comment.view.CommentSaveReq;
import com.monkey.domain.post.entity.Post;
import com.monkey.domain.post.entity.PostId;
import com.monkey.domain.post.repository.PostRepository;
import com.monkey.domain.post.view.PostSaveReq;
import com.monkey.domain.user.entity.User;
import com.monkey.domain.user.entity.UserId;
import com.monkey.domain.user.repository.UserRepository;
import com.monkey.domain.user.enums.UserSocial;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(readOnly = true)
public class CommentRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public User getUser() {
        User user = User.create("이지호", null, null, UserSocial.KAKAO);
        return userRepository.save(user);
    }

    @BeforeEach
    @Transactional
    public void 초기화() {
        User user = getUser();
        userRepository.save(user);

        PostSaveReq dto = new PostSaveReq("게시글");
        UserId userId = new UserId(user.getId());
        Post post = Post.create(userId, dto.getContent());
        postRepository.save(post);
    }

    @Test
    @Transactional
    public void 댓글_저장() {
        User user = userRepository.findById(1L).orElseThrow();
        Post post = postRepository.findById(1L).orElseThrow();

        CommentSaveReq dto1 = new CommentSaveReq(1L, null, "댓글1");
        CommentSaveReq dto2 = new CommentSaveReq(1L, 1L, "댓글2");

        Comment refComment = Comment.create(new UserId(user.getId()), null, new PostId(dto1.getPostId()), dto1.getContent());
        Comment comment = Comment.create(new UserId(user.getId()), refComment, new PostId(dto2.getPostId()), dto2.getContent());
        commentRepository.save(comment);


        List<Comment> comments = commentRepository.findAllByPostIdOrderByCreateAtDesc(new PostId(post.getId()));

        Comment result = comments.get(0);

        assertEquals(result.getUserId(), user.getId());
    }

    @Test
    @Transactional
    public void 대댓글_저장() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = postRepository.findById(1L).orElseThrow();

        CommentSaveReq dto1 = new CommentSaveReq(post.getId(), null, "댓글1");
        Comment comment1 = Comment.create(new UserId(user.getId()), null, new PostId(dto1.getPostId()), dto1.getContent());

        Comment refComment = commentRepository.save(comment1);
        CommentSaveReq dto2 = new CommentSaveReq(post.getId(), refComment.getId(), "댓글2");
        Comment comment1_1 = Comment.create(new UserId(user.getId()), refComment, new PostId(dto2.getPostId()), dto2.getContent());

        //when
        commentRepository.save(comment1_1);

        //then
        assertEquals(comment1_1.getRefComment(), comment1);
    }

    @Test
    @Transactional
    public void 대댓글_불러오기() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = postRepository.findById(1L).orElseThrow();
        CommentSaveReq dto1 = new CommentSaveReq(1L, null, "댓글1");
        Comment comment1 = commentRepository.save(Comment.create(new UserId(user.getId()), null, new PostId(dto1.getPostId()), dto1.getContent()));

        CommentSaveReq dto2 = new CommentSaveReq(1L, comment1.getId(), "대댓글1");
        Comment comment1_1 = commentRepository.save(Comment.create(new UserId(user.getId()), comment1, new PostId(dto2.getPostId()), dto2.getContent()));

        CommentSaveReq dto3 = new CommentSaveReq(1L, comment1.getId(), "대댓글2");
        Comment comment1_2 = commentRepository.save(Comment.create(new UserId(user.getId()), comment1, new PostId(dto3.getPostId()), dto3.getContent()));

        //when
        List<Comment> comments = commentRepository.findAllByRefComment(comment1);

        //then
        assertEquals(2, comments.size());
    }
}
