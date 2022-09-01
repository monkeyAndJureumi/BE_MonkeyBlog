package com.monkey.domain.user.root.service;

import com.monkey.domain.user.root.entity.User;
import com.monkey.domain.user.root.exception.UserNotFoundException;
import com.monkey.domain.user.root.repository.UserRepository;
import com.monkey.domain.user.root.view.UserSaveReq;
import com.monkey.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public Long getUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(ErrorCode.E000)).getId();
    }
    @Transactional
    public Long saveUser(UserSaveReq req) {
        User user = User.create(req.getName(), req.getEmail(), req.getNumber(), req.getSocial());
        return userRepository.save(user).getId();
    }
}
