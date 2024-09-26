package com.pikachu.purple.application.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IdUtil {

    public static Long generateId() {
        return Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS")));
    }

    public static Long from(String id) {
        return Long.parseLong(id);
    }

    public static String toString(Long id){
        return String.valueOf(id);
    }

}
