package com.monkey.context.member.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.member.domain.Member;
import com.monkey.context.member.enums.MemberSkill;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.dto.member.MemberProfileSaveDto;
import com.monkey.context.member.enums.OAuthType;
import com.monkey.context.member.infra.repository.MemberRepository;
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
    MemberRepository memberRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void initUser() {
        memberRepository.save(new Member(getOAuthUserInfo()));
    }

    @DisplayName("유저 프로필 저장")
    @Test
    public void setProfile() {
        //given
        saveProfile();

        //then
        Member result = memberRepository.findById(new MemberId("test")).orElseThrow();
        assertEquals(2, result.getProfile().getSkillList().size());
    }


    private void saveProfile() {
        Map<String, Object> objectMap = new HashMap<>();
        Set<MemberSkill> memberSkills = new LinkedHashSet<>();
        memberSkills.addAll(List.of(MemberSkill.JAVA, MemberSkill.SPRING));
        objectMap.put("skill_list", memberSkills);
        MemberProfileSaveDto dto = objectMapper.convertValue(objectMap, MemberProfileSaveDto.class);

        //when
        Member members = memberRepository.findById(new MemberId("")).orElseThrow();
    }

    private OAuthUserInfoDto getOAuthUserInfo() {
        return new OAuthUserInfoDto() {
            @Override
            public OAuthType getOAuthType() {
                return OAuthType.KAKAO;
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
                return new MemberId(OAuthType.KAKAO + "_" + getId());
            }
        };
    }
}
