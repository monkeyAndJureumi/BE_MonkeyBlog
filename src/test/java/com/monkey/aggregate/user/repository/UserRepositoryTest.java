package com.monkey.aggregate.user.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.aggregate.user.domain.UserProfile;
import com.monkey.aggregate.user.enums.UserSkill;
import com.monkey.aggregate.user.domain.User;
import com.monkey.aggregate.user.domain.UserId;
import com.monkey.aggregate.user.dto.social.OAuthUserInfo;
import com.monkey.aggregate.user.dto.user.UserProfileSaveDto;
import com.monkey.aggregate.user.dto.user.UserProfileUpdateDto;
import com.monkey.aggregate.user.enums.OauthType;
import com.monkey.aggregate.user.infra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=local")
@Transactional
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void initUser() {
        userRepository.save(new User(getOAuthUserInfo()));
    }

    @DisplayName("유저 프로필 저장")
    @Test
    public void setProfile() {
        //given
        saveProfile();

        //then
        User result = userRepository.findById(1L).orElseThrow();
        assertEquals(2, result.getProfile().getSkillList().size());
    }

    @DisplayName("유저 프로필 업데이트")
    @Test
    public void updateProfile() {
        //given
        saveProfile();
        Map<String, Object> objectMap = new HashMap<>();
        Set<UserSkill> userSkills = new LinkedHashSet<>();
        userSkills.addAll(List.of(UserSkill.SPRING));
        objectMap.put("skill_list", userSkills);
        UserProfileUpdateDto dto = objectMapper.convertValue(objectMap, UserProfileUpdateDto.class);

        //when
        UserProfile user = userRepository.findProfileByUserId(new UserId("gwqgrwq")).orElseThrow();
        user.update(dto);

        //then
        User result = userRepository.findById(new UserId("fdqgrqw")).orElseThrow();
        assertEquals(1, result.getProfile().getSkillList().size());
    }

    private void saveProfile() {
        Map<String, Object> objectMap = new HashMap<>();
        Set<UserSkill> userSkills = new LinkedHashSet<>();
        userSkills.addAll(List.of(UserSkill.JAVA, UserSkill.SPRING));
        objectMap.put("skill_list", userSkills);
        UserProfileSaveDto dto = objectMapper.convertValue(objectMap, UserProfileSaveDto.class);

        //when
        User user = userRepository.findById(new UserId("")).orElseThrow();
    }

    private OAuthUserInfo getOAuthUserInfo() {
        return new OAuthUserInfo() {
            @Override
            public OauthType getSocialType() {
                return OauthType.KAKAO;
            }

            @Override
            public Long getId() {
                return 15415348124L;
            }

            @Override
            public String getName() {
                return "테스트 이름";
            }

            @Override
            public String getImageUrl() {
                return "https://g4f6dsh8t944g631g4563";
            }

            @Override
            public String getNickName() {
                return "JJangChen";
            }

            @Override
            public String getEmail() {
                return "test@gmail.com";
            }

            @Override
            public String getAgeRange() {
                return null;
            }

            @Override
            public String getBirthDay() {
                return null;
            }

            @Override
            public String getGender() {
                return null;
            }

            @Override
            public String getPhoneNumber() {
                return null;
            }

            @Override
            public UserId getUserId() {
                return new UserId(OauthType.KAKAO + "_" + getId());
            }
        };
    }
}
