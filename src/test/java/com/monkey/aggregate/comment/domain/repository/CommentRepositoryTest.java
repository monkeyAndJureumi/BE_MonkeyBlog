package com.monkey.aggregate.comment.domain.repository;

import com.monkey.aggregate.comment.domain.Comment;
import com.monkey.aggregate.comment.domain.CommentAuthor;
import com.monkey.aggregate.comment.repository.CommentRepository;
import com.monkey.aggregate.post.domain.Post;
import com.monkey.aggregate.post.domain.PostAuthor;
import com.monkey.aggregate.post.domain.PostId;
import com.monkey.aggregate.post.infra.repository.PostRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.enums.UserSocial;
import com.monkey.aggregate.user.repository.UserRepository;
import com.monkey.exception.ErrorCode;
import com.monkey.exception.MonkeyException;
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
        return userRepository.save(User.create("Test", "test@mail.com", "010-1111-1111", UserSocial.KAKAO));
    }

    private Post initPost(Long userId, String userName) {
        return postRepository.save(Post.create(new PostAuthor(new UserId(userId), userName), "Test Content", false));
    }

    @DisplayName("findById 메서드 테스트")
    @Test
    public void selectComment() {
        //given
        User user = initUser();
        Post post = initPost(user.getId(), user.getName());
        Comment comment = Comment.create(new CommentAuthor(new UserId(user.getId()), user.getName()), null, new PostId(post.getId()), "Test Content", false);
        commentRepository.save(comment);

        //when
        Comment result = commentRepository.findById(1L).orElseThrow();

        //then
        assertEquals(comment.getContent(), result.getContent());
    }

    @DisplayName("findAllByRefComment_Id 메서드 테스트")
    @Test
    public void selectReplyComment() {
        //given
        User user = initUser();
        Post post = initPost(user.getId(), user.getName());
        Comment refComment = Comment.create(new CommentAuthor(new UserId(user.getId()), user.getName()), null, new PostId(post.getId()), "Test Ref Content", false);
        commentRepository.save(refComment);

        Comment replyComment = Comment.create(new CommentAuthor(new UserId(user.getId()), user.getName()), refComment, new PostId(post.getId()), "Test Reply Content", false);
        commentRepository.save(replyComment);

        //when
        List<Comment> result = commentRepository.findAllByRefComment_Id(refComment.getId());

        //then
        assertEquals(1, result.size());
        assertEquals("Test Reply Content", result.get(0).getContent());
    }

    @DisplayName("Comment_삭제")
    @Test
    public void deleteComment() {
        //given
        User user = initUser();
        Post post = initPost(user.getId(), user.getName());
        Comment refComment = Comment.create(new CommentAuthor(new UserId(user.getId()), user.getName()), null, new PostId(post.getId()), "Test Ref Content", false);
        commentRepository.save(refComment);
        commentRepository.delete(refComment);

        //when
        MonkeyException exception = assertThrows(MonkeyException.class, () -> {
            commentRepository.findById(1L).orElseThrow(() -> new MonkeyException(ErrorCode.E200));
        });

        //then
        assertEquals(ErrorCode.E200, exception.getErrorCode());
    }
}
