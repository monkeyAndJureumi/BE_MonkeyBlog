package com.monkey.aggregate.user.dto.social.kakao;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.monkey.aggregate.user.dto.social.UserInfo;
import com.monkey.aggregate.user.enums.SocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoUserInfoResponseDto extends UserInfo {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("has_signed_up")
    private Boolean hasSignedUp;

    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    @JsonProperty("synched_at")
    private LocalDateTime synchedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    public Long getId() {
        return id;
    }
    public SocialType getSocialType() {
        return SocialType.KAKAO;
    }
    public String getName() {
        return kakaoAccount.getName();
    }

    public String getImageUrl() {
        return kakaoAccount.getProfile().getProfileImageUrl();
    }

    public String getNickName() {
        return kakaoAccount.getProfile().getNickName();
    }

    public String getEmail() {
        return kakaoAccount.getEmail();
    }

    public String getAgeRange() {
        return kakaoAccount.getAgeRange();
    }

    public String getBirthDay() {
        return kakaoAccount.getBirthday();
    }

    public String getGender() {
        return kakaoAccount.getGender();
    }

    public String getPhoneNumber() {
        return kakaoAccount.getPhoneNumber();
    }

    public Boolean getHasSignedUp() {
        return hasSignedUp;
    }

    public LocalDateTime getConnectedAt() {
        return connectedAt;
    }

    public LocalDateTime getSynchedAt() {
        return synchedAt;
    }

    public KakaoAccount getKakaoAccount() {
        return kakaoAccount;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class KakaoAccount {
        @JsonProperty("profile_needs_agreement")
        private Boolean profileNeedsAgreement;

        @JsonProperty("profile_nickname_needs_agreement")
        private Boolean profileNicknameNeedsAgreement;

        @JsonProperty("profile_image_needs_agreement")
        private Boolean profileImageNeedsAgreement;

        @JsonProperty("profile")
        private Profile profile;

        @JsonProperty("name_needs_agreement")
        private Boolean nameNeedsAgreement;

        @JsonProperty("name")
        private String name;

        @JsonProperty("email_needs_agreement")
        private Boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private Boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private Boolean isEmailVerified;

        @JsonProperty("email")
        private String email;

        @JsonProperty("age_range_needs_agreement")
        private Boolean ageRangeNeedsAgreement;

        @JsonProperty("age_range")
        private String ageRange;

        @JsonProperty("birthyear_needs_agreement")
        private Boolean birthYearNeedsAgreement;

        @JsonProperty("birthyear")
        private String birthYear;

        @JsonProperty("birthday_needs_agreement")
        private Boolean birthdayNeedsAgreement;

        @JsonProperty("birthday")
        private String birthday;

        @JsonProperty("gender_needs_agreement")
        private Boolean genderNeedsAgreement;

        @JsonProperty("gender")
        private String gender;

        @JsonProperty("phone_number_needs_agreement")
        private Boolean phoneNumberNeedsAgreement;

        @JsonProperty("phone_number")
        private String phoneNumber;

        public Boolean getProfileNeedsAgreement() {
            return profileNeedsAgreement;
        }

        public Boolean getProfileNicknameNeedsAgreement() {
            return profileNicknameNeedsAgreement;
        }

        public Boolean getProfileImageNeedsAgreement() {
            return profileImageNeedsAgreement;
        }

        public Profile getProfile() {
            return profile;
        }

        public Boolean getNameNeedsAgreement() {
            return nameNeedsAgreement;
        }

        public String getName() {
            return name;
        }

        public Boolean getEmailNeedsAgreement() {
            return emailNeedsAgreement;
        }

        public Boolean getIsEmailValid() {
            return isEmailValid;
        }

        public Boolean getIsEmailVerified() {
            return isEmailVerified;
        }

        public String getEmail() {
            return email;
        }

        public Boolean getAgeRangeNeedsAgreement() {
            return ageRangeNeedsAgreement;
        }

        public String getAgeRange() {
            return ageRange;
        }

        public Boolean getBirthYearNeedsAgreement() {
            return birthYearNeedsAgreement;
        }

        public String getBirthYear() {
            return birthYear;
        }

        public Boolean getBirthdayNeedsAgreement() {
            return birthdayNeedsAgreement;
        }

        public String getBirthday() {
            return birthday;
        }

        public Boolean getGenderNeedsAgreement() {
            return genderNeedsAgreement;
        }

        public String getGender() {
            return gender;
        }

        public Boolean getPhoneNumberNeedsAgreement() {
            return phoneNumberNeedsAgreement;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Profile {
            @JsonProperty("nickname")
            private String nickName;

            @JsonProperty("profile_image_url")
            private String profileImageUrl;

            public String getNickName() {
                return nickName;
            }

            public String getProfileImageUrl() {
                return profileImageUrl;
            }
        }
    }
}
