package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.TokenResponseDto;
import com.monkey.aggregate.user.dto.user.UserLoginDto;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.exception.UserNotFoundException;
import com.monkey.aggregate.user.dto.user.UserSaveDto;
import com.monkey.enums.MonkeyErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final SocialServiceFactory socialServiceFactory;

    public Long getUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(MonkeyErrorCode.E000)).getId();
    }

    public void login(UserLoginDto dto) {
        SocialService service = socialServiceFactory.getSocialService(dto);
        TokenResponseDto tokenDto = service.getTokenDto(dto.getAuthorizeCode());
    }

    @Transactional
    public Long saveUser(UserSaveDto req) {
        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .number(req.getNumber())
                .build();
        return userRepository.save(user).getId();
    }
}
