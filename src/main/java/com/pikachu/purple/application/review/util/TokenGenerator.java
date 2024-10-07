package com.pikachu.purple.application.review.util;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

}
