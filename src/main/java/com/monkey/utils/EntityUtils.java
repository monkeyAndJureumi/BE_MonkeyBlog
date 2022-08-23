package com.monkey.utils;

import com.monkey.enums.EntityEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityUtils {

    /**
     * *
     * @param attribute 컨버팅 할 대상
     * @param isEncrypt 암호화 여부
     * @return DB에 저장되는 값
     */
    public static <T extends Enum<T> & EntityEnum> String EnumToDbData(T attribute, boolean isEncrypt) {
        if (attribute == null)
            return "";

        String result = attribute.getName();

        if (isEncrypt)
            result = EncodeAES256(result);
        return result;
    }

    /**
     * *
     * @param className EntityEnum 클래스를 가지는 Class 객체
     * @param dbData DB에 저장된 값
     * @param isEncrypt 암호화 여부
     * @return EntityEnum 구현 클래스
     */
    public static <T extends Enum<T> & EntityEnum> T DbDataToEnum(Class<T> className, String dbData, boolean isEncrypt) {
        if (dbData.isBlank() || dbData.isEmpty())
            return null;

        if (isEncrypt)
            dbData = DecodeAES256(dbData);

        final String data = dbData;

        return EnumSet.allOf(className).stream()
                .filter(e -> e.getName().equals(data))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(className.getName() + "와 일치하는 값을 찾을 수 없습니다."));
    }

    public static String EncodeAES256(String value) {
        return value;
    }

    public static String DecodeAES256(String value) {
        return value;
    }
}
