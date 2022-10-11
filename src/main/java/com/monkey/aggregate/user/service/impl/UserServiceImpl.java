package com.monkey.aggregate.user.service.impl;

import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.domain.UserProfile;
import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aggregate.user.enums.OauthType;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.service.OAuthService;
import com.monkey.aggregate.user.service.OAuthServiceFactory;
import com.monkey.aggregate.user.service.UserService;
import com.monkey.aop.permission.service.PermissionService;
import com.monkey.enums.MonkeyErrorCode;
import com.monkey.exception.MonkeyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PermissionService permissionService;
    private final OAuthServiceFactory OAuthServiceFactory;

    @Transactional
    @Override
    public void updateProfile(UserId userId, UserProfileUpdateDto dto) {
        UserProfile profile = userRepository.findProfileByUserId(userId).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E000));
        permissionService.checkPermission(userId, profile);
        profile.update(dto);
    }

    @Transactional
    @Override
    public void deactivateUser(UserId userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new MonkeyException(MonkeyErrorCode.E000));
        permissionService.checkPermission(userId, user);
        user.deactivate();
    }

    @Transactional
    @Override
    public UserId getUserIdOrElseCreate(OauthType oauthType, String accessToken) {
        OAuthUserInfo userInfo = getUserInfo(oauthType, accessToken);
        User result = userRepository.findById(new UserId(oauthType + "_" + userInfo.getId()))
                .orElseGet(() -> {
                    User user = userRepository.save(new User(userInfo));
                    user.setProfile(new UserProfile(userInfo));
                    return user;
                });
        return result.getUserId();
    }

    private OAuthUserInfo getUserInfo(OauthType oauthType, String accessToken) {
        OAuthService service = OAuthServiceFactory.getSocialService(oauthType);
        return service.getUserInfo(accessToken);
    }
}
