package com.pikachu.purple.common.vo;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Url {

    private static final String VALIDATION_REGEX = "^(http|https)://.*";
    private static final String VALIDATION_MESSAGE = "잘못된 URL 형식입니다.";

    @Pattern(regexp=VALIDATION_REGEX, message = VALIDATION_MESSAGE)
    private String value;
}
