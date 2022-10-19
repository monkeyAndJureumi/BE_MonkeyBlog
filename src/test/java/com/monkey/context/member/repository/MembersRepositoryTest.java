package com.monkey.context.member.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.domain.Members;
import com.monkey.context.member.domain.MemberProfile;
import com.monkey.context.member.enums.UserSkill;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.dto.oauth.OAuthUserInfo;
import com.monkey.context.member.dto.user.UserProfileSaveDto;
import com.monkey.context.member.dto.user.UserProfileUpdateDto;
import com.monkey.context.member.enums.OauthType;
import com.monkey.context.member.infra.repository.UserRepository;
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
public class MembersRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void initUser() {
        userRepository.save(new Members(getOAuthUserInfo()));
    }

    @DisplayName("유저 프로필 저장")
    @Test
    public void setProfile() {
        //given
        saveProfile();

        //then
        Members result = userRepository.findById(new MemberId("test")).orElseThrow();
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
        MemberProfile user = userRepository.findProfileByUserId(new MemberId("gwqgrwq")).orElseThrow();
        user.update(dto);

        //then
        Members result = userRepository.findById(new MemberId("fdqgrqw")).orElseThrow();
        assertEquals(1, result.getProfile().getSkillList().size());
    }

    private void saveProfile() {
        Map<String, Object> objectMap = new HashMap<>();
        Set<UserSkill> userSkills = new LinkedHashSet<>();
        userSkills.addAll(List.of(UserSkill.JAVA, UserSkill.SPRING));
        objectMap.put("skill_list", userSkills);
        UserProfileSaveDto dto = objectMapper.convertValue(objectMap, UserProfileSaveDto.class);

        //when
        Members members = userRepository.findById(new MemberId("")).orElseThrow();
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
            public MemberId getUserId() {
                return new MemberId(OauthType.KAKAO + "_" + getId());
            }
        };
    }
}
