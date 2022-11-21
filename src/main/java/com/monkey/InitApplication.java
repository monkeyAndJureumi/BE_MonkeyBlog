package com.monkey;

import com.monkey.context.member.domain.Member;
import com.monkey.context.member.domain.MemberId;
import com.monkey.context.member.dto.oauth.OAuthUserInfoDto;
import com.monkey.context.member.enums.OAuthType;
import com.monkey.context.member.infra.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class InitApplication implements ApplicationRunner {
    private final MemberRepository memberRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        memberRepository.save(new Member(new OAuthUserInfoDto() {
            @Override
            public OAuthType getOAuthType() {
                return OAuthType.KAKAO;
            }

            @Override
            public Long getId() {
                return 1111L;
            }

            @Override
            public String getName() {
                return "Test Name";
            }

            @Override
            public String getImageUrl() {
                return null;
            }

            @Override
            public String getNickName() {
                return null;
            }

            @Override
            public String getEmail() {
                return "Test@Test.mail";
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
        }));
    }
}
