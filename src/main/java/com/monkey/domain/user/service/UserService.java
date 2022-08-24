package com.monkey.domain.user.service;

import com.monkey.domain.user.entity.User;
import com.monkey.domain.user.exception.UserNotFoundException;
import com.monkey.domain.user.repository.UserRepository;
import com.monkey.domain.user.view.UserSaveReq;
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
