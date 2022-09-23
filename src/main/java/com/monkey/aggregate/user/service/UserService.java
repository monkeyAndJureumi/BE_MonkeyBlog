package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OAuthToken;
import com.monkey.aggregate.user.dto.social.UserInfo;
import com.monkey.aggregate.user.dto.token.TokenResponseDto;
import com.monkey.aggregate.user.dto.token.AccessRequestDto;
import com.monkey.aggregate.user.enums.GrantType;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.exception.UserNotFoundException;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final SocialServiceFactory socialServiceFactory;
    private final TokenService tokenService;

    public Long getUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(MonkeyErrorCode.E000)).getId();
    }

    public TokenResponseDto provideAccessToken(AccessRequestDto dto) {
        if (!dto.getGrantType().equals(GrantType.ACCESS_TOKEN))
            throw new MonkeyException(MonkeyErrorCode.E400);

        UserInfo userInfo = getOAuthUserInfo(dto);
        User user = userRepository.findByUserInfoIdAndAndUserInfoSocial(userInfo.getId(), dto.getSocialType())
                .orElseGet(() -> userRepository.save(new User(userInfo)));
        return tokenService.getToken(user);
    }

    private UserInfo getOAuthUserInfo(AccessRequestDto dto) {
        SocialService service = socialServiceFactory.getSocialService(dto.getSocialType());
        return service.getUserInfo(new OAuthToken(dto.getAccessToken()));
    }
}
