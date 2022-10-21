package com.monkey.converter;

import com.monkey.enums.ParamEnumerable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;

public class ParamToEnumConverterFactory implements ConverterFactory<String, Enum<? extends ParamEnumerable>> {
    @Override
    public <T extends Enum<? extends ParamEnumerable>> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter<>(targetType);
    }

    private static final class StringToEnumConverter<T extends Enum<? extends ParamEnumerable>> implements Converter<String, T> {
        private final Class<T> enumType;
        private final boolean enumerableParam;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
            this.enumerableParam = Arrays.stream(enumType.getInterfaces()).anyMatch(i -> i == ParamEnumerable.class);
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty())
                return null;

            T[] constants = enumType.getEnumConstants();
            for (T c : constants) {
                if (enumerableParam) {
                    if (((ParamEnumerable) c).getParam().equals(source.trim())) {
                        return c;
                    }
                } else {
                    if (c.name().equals(source.trim())) {
                        return c;
                    }
                }
            }
            return null;
        }
    }
}
