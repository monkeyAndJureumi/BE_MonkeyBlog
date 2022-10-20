package com.monkey.context.temp_post.event;

import com.monkey.context.temp_post.dto.RegisteredPost;
import com.monkey.context.temp_post.service.TempPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class TempPostEventHandler {
    private final TempPostService tempPostService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void deleteByTempPostId(RegisteredPost registeredPost) {
        tempPostService.delete(registeredPost.getMemberId(), registeredPost.getTempPostId());
    }

}
