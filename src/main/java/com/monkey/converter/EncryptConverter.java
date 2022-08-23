package com.monkey.converter;

import com.monkey.utils.EntityUtils;

import javax.persistence.AttributeConverter;

public class EncryptConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return EntityUtils.EncodeAES256(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return EntityUtils.DecodeAES256(dbData);
    }
}
