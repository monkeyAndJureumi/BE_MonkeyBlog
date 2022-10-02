package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.UserInfo;
import com.monkey.aggregate.token.dto.TokenRequestDto;
import com.monkey.aggregate.user.dto.user.UserIdentityDto;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.exception.UserNotFoundException;
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

    public UserIdentityDto getUserInfo(TokenRequestDto dto) {
        UserInfo userInfo = getOAuthUserInfo(dto);
        User user = userRepository.findByUserInfoIdAndAndUserInfoSocial(userInfo.getId(), dto.getSocial_type())
                .orElseGet(() -> userRepository.save(new User(userInfo)));
        return new UserIdentityDto(user);
    }

    private UserInfo getOAuthUserInfo(TokenRequestDto dto) {
        SocialService service = socialServiceFactory.getSocialService(dto.getSocial_type());
        return service.getUserInfo(new OauthToken(dto.getAccess_token()));
    }
}
