package com.pikachu.purple.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String today() {
        Instant now = Instant.now();
        ZonedDateTime today = now.atZone(ZoneId.systemDefault());
        return today.format(dateFormatter);
    }

    public static String yesterday() {
        Instant now = Instant.now();
        Instant yesterday = now.minus(1, ChronoUnit.DAYS);
        ZonedDateTime zonedDateTime = yesterday.atZone(ZoneId.systemDefault());
        return zonedDateTime.format(dateFormatter);
    }

    public static String theDayBeforeYesterday() {
        Instant now = Instant.now();
        Instant theDayBeforeYesterday = now.minus(2, ChronoUnit.DAYS);
        ZonedDateTime zonedDateTime = theDayBeforeYesterday.atZone(ZoneId.systemDefault());
        return zonedDateTime.format(dateFormatter);
    }

}
