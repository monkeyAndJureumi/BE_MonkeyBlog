package com.monkey.utils;

import com.monkey.enums.ZoneId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeUtils {
    public static String ConvertToString(LocalDateTime now, String id) {
        return id + "_" + now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static long ConvertToMillis(LocalDateTime now, ZoneId zoneId) {
        return now.toEpochSecond(ZoneOffset.of(zoneId.getOffset()));
    }
}
