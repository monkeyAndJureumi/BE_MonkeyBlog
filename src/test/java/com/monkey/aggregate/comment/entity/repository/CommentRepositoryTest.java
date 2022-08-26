package com.monkey.aggregate.comment.entity.repository;

import com.monkey.aggregate.comment.root.dto.CommentResponseDto;
import com.monkey.aggregate.comment.root.entity.Comment;
import com.monkey.aggregate.comment.root.entity.CommentId;
import com.monkey.aggregate.comment.root.repository.CommentRepository;
import com.monkey.aggregate.comment.root.dto.CommentSaveDto;
import com.monkey.aggregate.post.root.entity.Post;
import com.monkey.aggregate.post.root.entity.PostId;
import com.monkey.aggregate.post.root.repository.PostRepository;
import com.monkey.aggregate.post.root.view.PostSaveReq;
import com.monkey.aggregate.user.root.entity.User;
import com.monkey.aggregate.user.root.entity.UserId;
import com.monkey.aggregate.user.root.repository.UserRepository;
import com.monkey.aggregate.user.root.enums.UserSocial;
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

        Comment refComment = Comment.create(new UserId(user.getId()), null, new PostId(1L), "댓글1", false);
        Comment comment = Comment.create(new UserId(user.getId()), refComment, new PostId(1L), "댓글2", false);
        commentRepository.save(comment);


        List<Comment> comments = commentRepository.findAllByPostIdAndRefCommentIsNullOrderByCreatedAtDesc(new PostId(post.getId()));

        Comment result = comments.get(0);

        assertEquals(result.getUserId(), user.getId());
    }

    @Test
    @Transactional
    public void 대댓글_저장() {
        //given
        User user = userRepository.findById(1L).orElseThrow();
        Post post = postRepository.findById(1L).orElseThrow();

        Comment comment1 = Comment.create(new UserId(user.getId()), null, new PostId(1L), "댓글", false);

        Comment refComment = commentRepository.save(comment1);
        Comment comment1_1 = Comment.create(new UserId(user.getId()), refComment, new PostId(1L), "댓글2", false);

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
        Comment comment1 = commentRepository.save(Comment.create(new UserId(user.getId()), null, new PostId(1L), "댓글", false));

        Comment comment1_1 = commentRepository.save(Comment.create(new UserId(user.getId()), comment1, null, "대댓글1", false));

        CommentSaveDto dto3 = new CommentSaveDto(1L, comment1.getId(), "대댓글2", false);
        Comment comment1_2 = commentRepository.save(Comment.create(new UserId(user.getId()), comment1, null, "대댓글2", false));

        //when
        List<CommentResponseDto> comments = commentRepository.findAllByRefComment(new UserId(user.getId()), new CommentId(comment1.getId()));

        //then
        assertEquals(2, comments.size());
    }
}
