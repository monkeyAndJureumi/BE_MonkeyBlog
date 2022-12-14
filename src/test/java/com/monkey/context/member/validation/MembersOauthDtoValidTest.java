package com.monkey.context.member.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monkey.context.token.dto.TokenPostRequestDto;
import com.monkey.context.member.enums.OauthType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "spring.profiles.active=local")
public class MembersOauthDtoValidTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Validator validator;

    @Test
    public void 요청타입이_존재하지_않을_떄() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("grant_type", null);
        jsonObject.put("social_type", "kakao");
        jsonObject.put("access_token", "");
        jsonObject.put("refresh_token", "");
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //when
        Set<ConstraintViolation<TokenPostRequestDto>> validResult = validator.validate(dto);
        Iterator<ConstraintViolation<TokenPostRequestDto>> iterator = validResult.iterator();

        //then
        assertEquals("invalid grant_type", iterator.next().getMessage());
    }

    @Test
    public void 인가_토큰_값이_null_일때() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", null);
        jsonObject.put("social_type", "kakao");
        jsonObject.put("authorization_token", null);
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //when
        Iterator<ConstraintViolation<TokenPostRequestDto>> iterator = validDto(dto);

        //then
        assertEquals("authorization_token is not null", iterator.next().getMessage());
    }

    @Validated
    private Iterator<ConstraintViolation<TokenPostRequestDto>> validDto(TokenPostRequestDto dto) {
        Set<ConstraintViolation<TokenPostRequestDto>> validResult = validator.validate(dto);
        return validResult.iterator();
    }

    @Test
    public void 소셜_값이_null_일때() throws Exception {
    }

    @Test
    public void 리프레시_토큰_값이_null_일때() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "refresh_token");
        jsonObject.put("refresh_token", null);
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //when
        Set<ConstraintViolation<TokenPostRequestDto>> validResult = validator.validate(dto);
        Iterator<ConstraintViolation<TokenPostRequestDto>> iterator = validResult.iterator();

        //then
        assertEquals("refresh_token is not null", iterator.next().getMessage());
    }

    @Test
    public void 소셜_값이_없을_떄() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "access_token");
        jsonObject.put("social_type", "");
        jsonObject.put("access_token", "");
        jsonObject.put("refresh_token", "");
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //when
        Set<ConstraintViolation<TokenPostRequestDto>> validResult = validator.validate(dto);
        Iterator<ConstraintViolation<TokenPostRequestDto>> iterator = validResult.iterator();

        //then
        assertEquals("invalid social_type", iterator.next().getMessage());
    }

    @Test
    public void 소셜_값이_다른_값일_때() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "access_token");
        jsonObject.put("social_type", "gogle");
        jsonObject.put("access_token", "");
        jsonObject.put("refresh_token", "");
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //when
        Set<ConstraintViolation<TokenPostRequestDto>> validResult = validator.validate(dto);
        Iterator<ConstraintViolation<TokenPostRequestDto>> iterator = validResult.iterator();

        //then
        assertEquals("invalid social_type", iterator.next().getMessage());
    }

    @Test
    public void 소셜_값이_정상적으로_들어온다() throws JSONException, JsonProcessingException {
        //given
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "access_token");
        jsonObject.put("social_type", "KAKAO");
        jsonObject.put("access_token", "");
        jsonObject.put("refresh_token", "");

        //when
        TokenPostRequestDto dto = objectMapper.readValue(jsonObject.toString(), TokenPostRequestDto.class);

        //then
        assertEquals(OauthType.KAKAO, dto.getOauthType());
    }
}
