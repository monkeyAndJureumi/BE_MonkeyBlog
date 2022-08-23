package com.monkey.converter;

import com.monkey.enums.EntityEnum;
import com.monkey.utils.EntityUtils;

import javax.persistence.AttributeConverter;

public abstract class AbstractEnumConverter<T extends Enum<T> & EntityEnum> implements AttributeConverter<T, String> {
    private final Class<T> enumClass;
    private final String enumName;
    private final boolean isNullable;
    private final boolean isEncrypt;

    public AbstractEnumConverter(Class<T> enumClass, boolean isNullable, boolean isEncrypt) {
        this.enumClass = enumClass;
        this.enumName = enumClass.getTypeName();
        this.isNullable = isNullable;
        this.isEncrypt = isEncrypt;
    }

    /**
     * * Entity 클래스에 정의된 Enum 클래스를 문자열로 컨버팅하여 저장
     * @param attribute EntityEnum 구현하는 Enum 클래스
     * @return DB에 들어갈 값
     */
    @Override
    public String convertToDatabaseColumn(T attribute) {
        if (!isNullable && attribute == null)
            throw new IllegalStateException(enumName + "의 값은 null 이 될 수 없습니다.");

        return EntityUtils.EnumToDbData(attribute, isEncrypt);
    }

    /**
     * * DB에 저장된 값을 Enum 클래스와 일치하는지 확인하여 Enum 클래스로 반환
     * @param dbData  DB값
     * @return EntityEnum 구현하는 Enum 클래스
     */
    @Override
    public T convertToEntityAttribute(String dbData) {
        if (!isNullable && (dbData.isEmpty() || dbData.isBlank()))
            throw new IllegalStateException(enumName + "의 값이 null 입니다.");

        return EntityUtils.DbDataToEnum(enumClass, dbData, isEncrypt);
    }
}

