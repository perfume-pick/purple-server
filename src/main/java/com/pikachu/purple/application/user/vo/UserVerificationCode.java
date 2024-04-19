package com.pikachu.purple.application.user.vo;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserVerificationCode {

    private final String code;

    private static final int START_CAPITAL_NUMBER = 65;
    private static final int END_CAPITAL_NUMBER = 90;
    private static final int START_NUMBER = 48;
    private static final int END_NUMBER = 57;
    private static final int MAX_LENGTH = 3;
    private static final int TARGET_STRING_LENGTH = 6;

    public static UserVerificationCode createVerificationCode(){
        Random random = new SecureRandom();

        String upperCase = random.ints(
            START_CAPITAL_NUMBER,
                END_CAPITAL_NUMBER + 1
            )
            .limit(MAX_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        String numbers = random.ints(
            START_NUMBER,
                END_NUMBER + 1
            )
            .limit(MAX_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return new UserVerificationCode(shuffleString(upperCase + numbers));
    }

    private static String shuffleString(String input){
        List<Character> characters = input.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
        Collections.shuffle(characters);
        StringBuilder shuffleString = new StringBuilder();
        characters.forEach(shuffleString::append);
        return shuffleString.toString();
    }

}
