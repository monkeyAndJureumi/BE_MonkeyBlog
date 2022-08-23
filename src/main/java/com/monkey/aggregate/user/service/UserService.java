package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.exception.UserNotFoundException;
import com.monkey.aggregate.user.repository.UserRepository;
import com.monkey.aggregate.user.view.UserSaveReq;
import com.monkey.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public Long getUserId() {
        return userRepository.findById(1L).orElseThrow(() -> new UserNotFoundException(ErrorCode.E000)).getId();
    }
    @Transactional
    public void saveUser(UserSaveReq req) {
        User user = User.create(req.getName(), req.getEmail(), req.getNumber(), req.getSocial());
        userRepository.save(user);
    }
}
