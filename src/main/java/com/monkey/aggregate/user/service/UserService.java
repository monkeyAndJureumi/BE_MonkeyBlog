package com.monkey.aggregate.user.service;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.domain.UserInfo;
import com.monkey.aggregate.user.domain.UserProfile;
import com.monkey.aggregate.user.dto.social.OauthToken;
import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
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

    public UserId getUserId(UserId id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(MonkeyErrorCode.E000)).getId();
    }

    @Transactional
    public UserIdentityDto getUserInfo(TokenRequestDto dto) {
        OAuthUserInfo userInfo = getOAuthUserInfo(dto);
        User result = userRepository.findById(new UserId(dto.getSocialType() + "_" + userInfo.getId()))
                .orElseGet(() -> {
                    User user = userRepository.save(new User(userInfo));
                    user.setUserInfo(new UserInfo(userInfo));
                    user.setProfile(new UserProfile(userInfo));
                    return user;
                });
        return new UserIdentityDto(result);
    }

    private OAuthUserInfo getOAuthUserInfo(TokenRequestDto dto) {
        SocialService service = socialServiceFactory.getSocialService(dto.getSocialType());
        return service.getUserInfo(new OauthToken(dto.getAccessToken()));
    }
}
