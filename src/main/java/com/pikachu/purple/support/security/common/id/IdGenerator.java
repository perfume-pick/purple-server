package com.pikachu.purple.support.security.common.id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {

    public static Long generate() {
        return Long.parseLong(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSS")));
    }

}
